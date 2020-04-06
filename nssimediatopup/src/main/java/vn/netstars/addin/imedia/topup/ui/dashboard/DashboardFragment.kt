package vn.netstars.addin.imedia.topup.ui.dashboard

import kotlinx.android.synthetic.main._nss_fragment_dashboard.*
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.ui.gamecard.GameCardFragment
import vn.netstars.addin.imedia.topup.ui.phonecard.PhoneCardFragment
import vn.netstars.addin.imedia.topup.utils.extentions.nssNavigateToFragment
import vn.starpay.lite.base.BaseFragment
import vn.starpay.lite.utils.extensions.setSafeOnClickListener

class DashboardFragment : BaseFragment<DashboardViewModel>() {
    private val mPhoneCardFragment by lazy { PhoneCardFragment() }
    private val mGameCardFragment by lazy { GameCardFragment() }

    override fun getLayoutResId(): Int = R.layout._nss_fragment_dashboard
    override fun providerVMClass(): Class<DashboardViewModel>? = DashboardViewModel::class.java

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initEvent() {
        btn_phone_card.setSafeOnClickListener {
            nssNavigateToFragment(mPhoneCardFragment)
        }

        btn_game_card.setSafeOnClickListener {
            nssNavigateToFragment(mGameCardFragment)
        }
    }

}