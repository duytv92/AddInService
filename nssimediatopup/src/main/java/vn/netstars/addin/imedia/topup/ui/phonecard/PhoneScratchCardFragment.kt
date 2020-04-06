package vn.netstars.addin.imedia.topup.ui.phonecard

import androidx.core.os.bundleOf
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main._nss_component_cart.*
import kotlinx.android.synthetic.main._nss_fragment_phone_scratch_card.*
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.datasource.model.CardBrandModel
import vn.netstars.addin.imedia.topup.datasource.model.CartItemModel
import vn.netstars.addin.imedia.topup.ui.phonecardinvoice.PhoneCardInvoiceFragment
import vn.netstars.addin.imedia.topup.utils.extentions.nssNavigateToFragment
import vn.starpay.lite.base.BaseFragment
import vn.starpay.lite.utils.extensions.setSafeOnClickListener

class PhoneScratchCardFragment private constructor(): BaseFragment<PhoneCardViewModel>() {

    companion object {
        private const val LIST_CARD_BRANDS_KEY = "LIST_CARD_BRANDS_KEY"

        fun newInstance(listDataCard: ArrayList<CardBrandModel>): PhoneScratchCardFragment {

            val args = bundleOf(
                LIST_CARD_BRANDS_KEY to listDataCard
            )
            val fragment = PhoneScratchCardFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val mCardBrandHorizontalAdapter by lazy { CardBrandHorizontalAdapter() }
    private val mCardValueAdapter by lazy { CardValueAdapter() }
    private val mShoppingCartAdapter by lazy { ShoppingCartAdapter() }
    private var mCurrentBrandPosition = 0
    private var mOldPosition: Int = 0

    override fun getLayoutResId(): Int = R.layout._nss_fragment_phone_scratch_card
    override fun providerVMClass(): Class<PhoneCardViewModel>? = PhoneCardViewModel::class.java

    override fun initView() {
        val listDataCard =
            arguments?.getParcelableArrayList<CardBrandModel>(LIST_CARD_BRANDS_KEY) ?: arrayListOf()

        initCardBrandHorizontalAdapter(listDataCard)

        rv_cart.adapter = mShoppingCartAdapter

        initCardValuesRecycleView(listDataCard)
    }

    override fun initData() {

    }

    override fun initEvent() {
        btn_pay.setSafeOnClickListener {
            nssNavigateToFragment(PhoneCardInvoiceFragment.newInstance(mShoppingCartAdapter.data as ArrayList<CartItemModel>))
        }
    }

    private fun initCardBrandHorizontalAdapter(listBrands: ArrayList<CardBrandModel>) {
        if (listBrands.size > 0) mCardBrandHorizontalAdapter.rowIndex = 0

        rv_brand_name.adapter = mCardBrandHorizontalAdapter
        mCardBrandHorizontalAdapter.setNewData(listBrands)
        mCardBrandHorizontalAdapter.setOnItemClickListener { _, _, position ->
            mCurrentBrandPosition = position
            mCardBrandHorizontalAdapter.rowIndex = position
            mCardBrandHorizontalAdapter.notifyItemChanged(position)
            mCardBrandHorizontalAdapter.notifyItemChanged(mOldPosition)

            mOldPosition = position

            mCardValueAdapter.setNewData(mCardBrandHorizontalAdapter.data[position].cardValues)
        }
    }

    private fun initCardValuesRecycleView(listDataCard: ArrayList<CardBrandModel>) {
        val layoutManager = FlexboxLayoutManager(requireContext())
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.justifyContent = JustifyContent.FLEX_START
        rv_card_values_flex.layoutManager = layoutManager
        rv_card_values_flex.adapter = mCardValueAdapter

        if (listDataCard.isNotEmpty()) {
            mCardValueAdapter.setNewData(listDataCard[0].cardValues)
        }

        mCardValueAdapter.setOnItemClickListener { _, _, position ->
            val cardData = mCardValueAdapter.data[position]

            val shoppingCartData = mShoppingCartAdapter.data
            val updateIndex = shoppingCartData.indexOfFirst { it.id == cardData.id }

            if (updateIndex != -1) {
                mShoppingCartAdapter.data[updateIndex].quantity++
                mShoppingCartAdapter.notifyItemChanged(updateIndex)

            } else {
                mShoppingCartAdapter.addData(CartItemModel().apply {
                    this.id = cardData.id
                    this.cardType = listDataCard[mCurrentBrandPosition].productName
                    this.cardStringValue = cardData.name
                    this.cardPriceValue = cardData.value
                    this.quantity = 1
                })
            }
        }

        initShoppingCardAdapter()

    }

    private fun initShoppingCardAdapter() {
        mShoppingCartAdapter.setOnItemChildClickListener { _, view, position ->
            if (view.id == R.id.btn_cart_add) {
                mShoppingCartAdapter.data[position].quantity++
                mShoppingCartAdapter.notifyItemChanged(position)
            } else if (view.id == R.id.btn_cart_remove) {
                val quantityAfterUpdate = --mShoppingCartAdapter.data[position].quantity
                if (quantityAfterUpdate < 1) {
                    mShoppingCartAdapter.remove(position)
                } else {
                    mShoppingCartAdapter.notifyItemChanged(position)
                }

            }
        }
    }

}