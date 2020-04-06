package vn.netstars.addin.imedia.topup.ui.gamecard

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import vn.netstars.addin.imedia.topup.datasource.api.NITRetrofitClient
import vn.netstars.addin.imedia.topup.datasource.model.CardBrandModel
import vn.starpay.lite.base.BaseViewModel

class GameCardViewModel : BaseViewModel() {
    val listGameCardBrandData = MutableLiveData<ArrayList<CardBrandModel>>()

    val isGetQrListGameCardBrandFailed = MutableLiveData<Boolean>()

    fun getListGameCardBrands() {
        showLoading()
        NITRetrofitClient.instance.service.getListGameCardBrand()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ArrayList<CardBrandModel>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: ArrayList<CardBrandModel>) {
                    hideLoading()
                    listGameCardBrandData.value = t
                    isGetQrListGameCardBrandFailed.value = false
                }

                override fun onError(e: Throwable) {
                    isGetQrListGameCardBrandFailed.value = true
                    hideLoading()
                    onLoadFail(e)
                }

            })
    }
}