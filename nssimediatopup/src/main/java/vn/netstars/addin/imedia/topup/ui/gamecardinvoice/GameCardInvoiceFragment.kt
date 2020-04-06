package vn.netstars.addin.imedia.topup.ui.gamecardinvoice

import androidx.core.os.bundleOf
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.FragmentUtils
import com.chad.library.adapter.base.entity.MultiItemEntity
import kotlinx.android.synthetic.main._nss_fragment_game_card_invoice.*
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.datasource.model.CartItemModel
import vn.netstars.addin.imedia.topup.datasource.model.cardinvoice.InvoiceBrandItem
import vn.netstars.addin.imedia.topup.datasource.model.cardinvoice.InvoiceCardDetailItem
import vn.netstars.addin.imedia.topup.ui.gamecard.GameCardViewModel
import vn.starpay.lite.base.BaseFragment
import vn.starpay.lite.utils.extensions.setSafeOnClickListener
import java.text.DecimalFormat
import kotlin.math.absoluteValue
import kotlin.random.Random

class GameCardInvoiceFragment private constructor(): BaseFragment<GameCardViewModel>() {

    companion object {
        private const val LIST_ITEM_IN_CART_KEY = "LIST_ITEM_IN_CART_KEY"
        fun newInstance(listItemInCart: ArrayList<CartItemModel>): GameCardInvoiceFragment {
            val args = bundleOf(
                LIST_ITEM_IN_CART_KEY to listItemInCart
            )
            val fragment = GameCardInvoiceFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mCardInvoiceAdapter: CardInvoiceAdapter

    override fun getLayoutResId(): Int = R.layout._nss_fragment_game_card_invoice
    override fun providerVMClass(): Class<GameCardViewModel>? = GameCardViewModel::class.java

    override fun initView() {
        val listItemInCart: ArrayList<CartItemModel> =
            arguments?.getParcelableArrayList(LIST_ITEM_IN_CART_KEY) ?: arrayListOf()

        initCardInvoiceRecyclerView(listItemInCart)
        initTotalMoney(listItemInCart)

    }

    override fun initData() {

    }

    override fun initEvent() {
        btn_finish.setSafeOnClickListener {
            FragmentUtils.pop(requireActivity().supportFragmentManager)
            FragmentUtils.pop(requireActivity().supportFragmentManager)
        }

        btn_continue.setSafeOnClickListener {
            btn_continue.setSafeOnClickListener {
                MaterialDialog(requireContext()).show {
                    title(text = getString(R.string._nss_dialog_notification_title))
                    message(text = getString(R.string._nss_dialog_not_enough_money_message))
                    positiveButton(text = getString(R.string._nss_dialog_button_recharge)) {
                        it.dismiss()
                    }
                    negativeButton(text = getString(R.string._nss_dialog_button_cancel)) {
                        it.dismiss()
                    }
                    cornerRadius(6f)
                }
            }
        }
    }

    private fun initTotalMoney(listItemInCart: ArrayList<CartItemModel>) {
        var totalMoney = 0
        listItemInCart.forEach {
            totalMoney += it.quantity * it.cardPriceValue
        }
        val formatter = DecimalFormat("#,###")
        tv_total_money_value.text = "${formatter.format(totalMoney)} d"
    }

    private fun initCardInvoiceRecyclerView(listItemInCart: ArrayList<CartItemModel>) {
        mCardInvoiceAdapter = CardInvoiceAdapter(generateDataExpandable(listItemInCart))
        rv_card_invoice.adapter = mCardInvoiceAdapter
        mCardInvoiceAdapter.expandAll()
    }

    private fun generateDataExpandable(listItemInCart: ArrayList<CartItemModel>): ArrayList<MultiItemEntity>  {
        val res = arrayListOf<MultiItemEntity>()
        val listBrandItem = listItemInCart.map { it.cardType }.distinctBy { it }

        listBrandItem.forEach {
            val invoiceBrandItem = InvoiceBrandItem().apply { this.brandName = it }
            val listCardDetailByBrand = listItemInCart.filter { cart -> cart.cardType == it }

            listCardDetailByBrand.forEach { cardDetailItem ->
                for (i in 0 until cardDetailItem.quantity) {
                    invoiceBrandItem.addSubItem(InvoiceCardDetailItem().apply {
                        this.serial = Random.nextLong().absoluteValue.toString()
                        this.keyCard = Random.nextLong().absoluteValue.toString()
                        this.price = cardDetailItem.cardStringValue
                    })
                }

            }

            res.add(invoiceBrandItem)
        }

        return res
    }


}