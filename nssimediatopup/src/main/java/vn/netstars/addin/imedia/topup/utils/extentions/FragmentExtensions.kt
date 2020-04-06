package vn.netstars.addin.imedia.topup.utils.extentions

import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.CacheDiskUtils
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.SPUtils
import vn.netstars.addin.imedia.topup.R
import vn.starpay.lite.base.BaseFragment

fun BaseFragment<*>.nssNavigateToFragment(fragment: Fragment) {
    requireActivity().supportFragmentManager.beginTransaction()
        .setCustomAnimations(R.anim._nss_enter_animation, R.anim._nss_stay_animation, R.anim._nss_stay_animation, R.anim._nss_exit_animation)
        .add(R.id.frame_main, fragment)
        .addToBackStack(null)
        .commit()
}

fun BaseFragment<*>.nssReplaceWithFragment(fragment: Fragment) {
    requireActivity().supportFragmentManager.beginTransaction()
        .setCustomAnimations(R.anim._nss_enter_animation, R.anim._nss_stay_animation, R.anim._nss_stay_animation, R.anim._nss_exit_animation)
        .replace(R.id.frame_main, fragment)
        .addToBackStack(null)
        .commit()
}