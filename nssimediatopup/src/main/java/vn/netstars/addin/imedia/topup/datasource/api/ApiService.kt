package vn.netstars.addin.imedia.topup.datasource.api

import io.reactivex.Observable
import retrofit2.http.*
import vn.netstars.addin.imedia.topup.datasource.model.CardBrandModel

interface ApiService {

    @GET("getListGameCardBrand")
    fun getListGameCardBrand(): Observable<ArrayList<CardBrandModel>>

}