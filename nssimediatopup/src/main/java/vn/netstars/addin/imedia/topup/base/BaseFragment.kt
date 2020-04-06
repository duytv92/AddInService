package vn.starpay.lite.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ToastUtils
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.ui.parent.ParentFragment
import vn.netstars.addin.imedia.topup.utils.constants.MappingConst

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {
    lateinit var mView: View
    lateinit var viewModel: VM
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(getLayoutResId(), container, false)
        return mView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVM()
        initView()
        initData()
        initEvent()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mView.findViewById<ImageView>(R.id.iv_back_button)?.let {
            it.setOnClickListener {
                requireActivity().onBackPressed()
                onBackPress()
            }
        }

        observeProgressBarEvent()
        observeErrorMessage()
    }

    open fun setupUI(view: View) { // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                KeyboardUtils.hideSoftInput(view)
                false
            }
        }
        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }

    private fun initVM() {
        providerVMClass()?.let {
            viewModel = ViewModelProvider(this).get(it)
        }
    }

    private fun observeProgressBarEvent() {
        viewModel.getLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) showLoading()
            else hideLoading()
        })
    }

    private fun observeErrorMessage() {
        viewModel.getErrorMessage().observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                onLoadDataFail()
                ToastUtils.showLong(
                    MappingConst.SERVER_ERROR_RESPONSE_MAPPING[it]?.let { value ->
                        getString(value)
                    } ?: kotlin.run {
                        errorMessage
                    }
                )

                //process log out if 401 error here
            }
        })

    }

    open fun onBackPress() {
    }

    protected fun showLoading() {
        val parentFragment = FragmentUtils.findFragment(requireActivity().supportFragmentManager, getString(R.string._nss_parent_tag))
        if (parentFragment != null && parentFragment is ParentFragment) {
            parentFragment.showParentLoading()
        }
    }

    protected fun hideLoading() {
        val parentFragment = FragmentUtils.findFragment(requireActivity().supportFragmentManager, getString(R.string._nss_parent_tag))
        if (parentFragment != null && parentFragment is ParentFragment) {
            parentFragment.hideParentLoading()
        }
    }

    open fun onLoadDataFail() {
    }


    abstract fun getLayoutResId(): Int
    abstract fun providerVMClass(): Class<VM>?
    abstract fun initView()
    abstract fun initData()
    abstract fun initEvent()
}
