package vn.netstars.addin.imedia.topup.ui.phonecard

import com.blankj.utilcode.util.ColorUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.datasource.model.CardBrandModel

class CardBrandHorizontalAdapter : BaseQuickAdapter<CardBrandModel, BaseViewHolder>
    (R.layout._nss_item_card_brand_horizontal) {

    var rowIndex = -1

    override fun convert(helper: BaseViewHolder, item: CardBrandModel?) {
        helper.run {
            setText(R.id.btn_brand_name, item?.productName)

            if (rowIndex == helper.adapterPosition) {
                setBackgroundColor(R.id.btn_brand_name, ColorUtils.getColor(R.color._nss_color_accent))
            } else {
                setBackgroundColor(R.id.btn_brand_name, ColorUtils.getColor(R.color._nss_color_background_cart))
            }
        }
    }

}