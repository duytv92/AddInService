package vn.netstars.addin.imedia.topup.ui.phonecard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.blankj.utilcode.util.StringUtils
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.datasource.model.CardBrandModel

class PhoneCardPagerAdapter(fm: FragmentManager, behavior: Int, listCardBrands: ArrayList<CardBrandModel>) :
    FragmentPagerAdapter(fm, behavior) {

    private var mListCardBrands: ArrayList<CardBrandModel> = listCardBrands

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> PhonePrepayFragment.newInstance(mListCardBrands)
            1 -> PhoneScratchCardFragment.newInstance(mListCardBrands)
            else -> PhonePrepayFragment.newInstance(mListCardBrands)
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? = when(position) {
        0 -> StringUtils.getString(R.string._nss_phone_prepay_title)
        1 -> StringUtils.getString(R.string._nss_phone_scratch_card_title)
        else -> ""
    }
}