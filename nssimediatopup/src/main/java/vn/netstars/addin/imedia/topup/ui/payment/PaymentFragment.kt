package vn.netstars.addin.imedia.topup.ui.payment

import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main._nss_fragment_payment.*
import kotlinx.android.synthetic.main._nss_top_bar_navigation.*
import vn.netstars.addin.imedia.topup.R
import vn.starpay.lite.base.BaseFragment

class PaymentFragment : BaseFragment<PaymentViewModel>() {

    override fun getLayoutResId(): Int = R.layout._nss_fragment_payment
    override fun providerVMClass(): Class<PaymentViewModel>? = PaymentViewModel::class.java

    override fun initView() {
        val phoneCardPagerAdapter = PaymentPagerAdapter(requireActivity().supportFragmentManager,
            FragmentPagerAdapter.POSITION_UNCHANGED)

        viewPager.adapter = phoneCardPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun initData() {
        txtTopBarTitle.text = "Nạp tiền vào ví"
    }

    override fun initEvent() {

    }

}