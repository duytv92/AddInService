package vn.netstars.addin.imedia.topup.ui.parent

import android.view.View
import kotlinx.android.synthetic.main._nss_component_progress_loading.*
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.ui.dashboard.DashboardFragment
import vn.starpay.lite.base.BaseFragment

class ParentFragment : BaseFragment<ParentViewModel>() {

    override fun getLayoutResId(): Int = R.layout._nss_fragment_parent
    override fun providerVMClass(): Class<ParentViewModel>? = ParentViewModel::class.java

    override fun initView() {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.frame_main, DashboardFragment())
            .commit()
    }

    override fun initData() {

    }

    override fun initEvent() {

    }

    fun showParentLoading() {
        progress_loading_layout.visibility = View.VISIBLE
    }

    fun hideParentLoading() {
        progress_loading_layout.visibility = View.GONE
    }

}