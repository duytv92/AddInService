package vn.netstars.addin.imedia.topup.ui.phonecard

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.datasource.model.CardPriceModel

class CardValueAdapter : BaseQuickAdapter<CardPriceModel, BaseViewHolder>
    (R.layout._nss_item_card_brand_horizontal) {

    override fun convert(helper: BaseViewHolder, item: CardPriceModel?) {
        helper.run {
            setText(R.id.btn_brand_name, item?.name)

        }
    }

}