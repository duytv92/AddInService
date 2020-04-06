package vn.netstars.addin.imedia.topup.ui.payment

import vn.netstars.addin.imedia.topup.R
import vn.starpay.lite.base.BaseFragment

class BankFragment : BaseFragment<PaymentViewModel>() {

    override fun getLayoutResId(): Int = R.layout._nss_fragment_bank
    override fun providerVMClass(): Class<PaymentViewModel>? = PaymentViewModel::class.java

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initEvent() {

    }

}