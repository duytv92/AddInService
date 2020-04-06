package vn.netstars.addin.imedia.topup.ui.payment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.blankj.utilcode.util.StringUtils
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.datasource.model.CardBrandModel

class PaymentPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> BankFragment()
            1 -> SupportFragment()
            else -> BankFragment()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? = when(position) {
        0 -> StringUtils.getString(R.string.nss_title_payment)
        1 -> StringUtils.getString(R.string.nss_ttitle_support_payment)
        else -> ""
    }
}