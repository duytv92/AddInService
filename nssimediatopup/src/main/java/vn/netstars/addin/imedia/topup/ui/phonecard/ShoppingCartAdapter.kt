package vn.netstars.addin.imedia.topup.ui.phonecard

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import vn.netstars.addin.imedia.topup.R
import vn.netstars.addin.imedia.topup.datasource.model.CartItemModel

class ShoppingCartAdapter : BaseQuickAdapter<CartItemModel, BaseViewHolder>
    (R.layout._nss_item_cart) {

    override fun convert(helper: BaseViewHolder, item: CartItemModel?) {
        helper.run {
            setText(R.id.tv_cart_card_name, item?.cardType)
            setText(R.id.tv_cart_card_value, item?.cardStringValue)
            setText(R.id.tv_cart_quantity, item?.quantity.toString())

            addOnClickListener(R.id.btn_cart_add)
            addOnClickListener(R.id.btn_cart_remove)
        }
    }

}