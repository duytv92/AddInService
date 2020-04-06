package vn.netstars.addin.imedia.topup.datasource.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vn.netstars.addin.imedia.topup.datasource.model.CardBrandModel
import java.io.IOException

object LocalDataSource {
    private fun getJsonFromAssets(context: Context, fileName: String = "fakedata.json"): String? {
        try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            return String(buffer, Charsets.UTF_8)

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun getListCardBrand(context: Context, type: CardType = CardType.PHONE): ArrayList<CardBrandModel> {
        val jsonFakeData = getJsonFromAssets(context)
        if (jsonFakeData.isNullOrBlank()) return arrayListOf()

        val typeParse = object : TypeToken<ArrayList<CardBrandModel>>() {}.type
        val dataParse: ArrayList<CardBrandModel> = Gson().fromJson(jsonFakeData, typeParse)

        return dataParse.filter { it.productTypeKey == type.key } as ArrayList<CardBrandModel>

    }
}

enum class CardType(val key: String){
    PHONE("PHONE"),
    GAME("GAME")
}