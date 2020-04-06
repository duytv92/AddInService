package vn.netstars.addin.imedia.topup.datasource.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardItem(
    /*@SerializedName("productId") val productId : Int,
    @SerializedName("type") val type : Int, //1: Viettel, 2: Vinaphone, 3:Mobifone, 4: Beeline, 5: vnmobile
                                                    //6: Vcoin, 7: Gate*/
    @SerializedName("productName") val productName : String,
    @SerializedName("value") val value : String
) : Parcelable