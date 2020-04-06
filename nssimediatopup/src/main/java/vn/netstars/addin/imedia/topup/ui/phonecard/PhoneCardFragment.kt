package vn.netstars.addin.imedia.topup.ui.phonecard

import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main._nss_fragment_phone_card.*
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.datasource.local.LocalDataSource
import vn.starpay.lite.base.BaseFragment

class PhoneCardFragment : BaseFragment<PhoneCardViewModel>() {

    override fun getLayoutResId(): Int = R.layout._nss_fragment_phone_card
    override fun providerVMClass(): Class<PhoneCardViewModel>? = PhoneCardViewModel::class.java

    override fun initView() {
        val listBrands = LocalDataSource.getListCardBrand(requireContext())

        val phoneCardPagerAdapter = PhoneCardPagerAdapter(requireActivity().supportFragmentManager,
            FragmentPagerAdapter.POSITION_UNCHANGED, listBrands)

        pager_phone_card.adapter = phoneCardPagerAdapter
        tabs_phone_card.setupWithViewPager(pager_phone_card)

    }

    override fun initData() {

    }

    override fun initEvent() {

    }

}