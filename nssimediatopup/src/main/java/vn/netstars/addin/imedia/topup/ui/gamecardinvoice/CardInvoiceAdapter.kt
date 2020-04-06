package vn.netstars.addin.imedia.topup.ui.gamecardinvoice

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.datasource.model.cardinvoice.InvoiceBrandItem
import vn.netstars.addin.imedia.topup.datasource.model.cardinvoice.InvoiceCardDetailItem

class CardInvoiceAdapter(data: MutableList<MultiItemEntity>?) :
    BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {

    companion object {
        const val TYPE_INVOICE_CARD_BRAND = 0
        const val TYPE_INVOICE_CARD_VALUE = 1
    }

    init {
        addItemType(TYPE_INVOICE_CARD_BRAND, R.layout._nss_item_card_invoice_lv0)
        addItemType(TYPE_INVOICE_CARD_VALUE, R.layout._nss_item_card_invoice_lv1)
    }

    override fun convert(helper: BaseViewHolder, item: MultiItemEntity?) {
        when (helper.itemViewType) {
            TYPE_INVOICE_CARD_BRAND -> {

                val lv0CardBrand = item as InvoiceBrandItem

                helper.setText(R.id.tv_invoice_lv0_title, lv0CardBrand.brandName)
            }
            TYPE_INVOICE_CARD_VALUE -> {
                val lv1CardDetail = item as InvoiceCardDetailItem

                helper.run {
                    setText(R.id.tv_serial_value, lv1CardDetail.serial)
                    setText(R.id.tv_key_card_value, lv1CardDetail.keyCard)
                    setText(R.id.tv_card_price_value, lv1CardDetail.price)
                }

            }
        }
    }

}