package vn.netstars.addin.imedia.topup.ui.infocard

import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main._nss_fragment_card_info.*
import kotlinx.android.synthetic.main._nss_top_bar_navigation.*
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.datasource.model.CardItem
import vn.netstars.addin.imedia.topup.ui.payment.PaymentFragment
import vn.netstars.addin.imedia.topup.utils.extentions.nssNavigateToFragment
import vn.starpay.lite.base.BaseFragment
import vn.starpay.lite.utils.extensions.setSafeOnClickListener

class CardInfoFragment : BaseFragment<CardInfoViewModel>() {

    override fun getLayoutResId(): Int = R.layout._nss_fragment_card_info
    override fun providerVMClass(): Class<CardInfoViewModel>? = CardInfoViewModel::class.java

    companion object {
        private const val LIST_ITEM = "list_item"
        fun newInstance(listDataCard: ArrayList<CardItem>): CardInfoFragment {
            val args = bundleOf(
                LIST_ITEM to listDataCard
            )
            val fragment = CardInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView() {


    }

    override fun initData() {
        txtTopBarTitle.text = "Thông tin chi tiết"
        val listDataCard =
            arguments?.getParcelableArrayList<CardItem>(LIST_ITEM) ?: arrayListOf()
        if (listDataCard.size > 0) {
            val item = listDataCard[0]

            txtTelecomTitle.text = "Nhà mạng: " + item.productName
            txtMoney.text = "Số tiền: " + item.value
        }
    }

    override fun initEvent() {
        btnPayment.setSafeOnClickListener {
            nssNavigateToFragment(PaymentFragment())
        }
    }

}