package vn.netstars.addin.imedia.topup.datasource.model.cardinvoice;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import vn.netstars.addin.imedia.topup.ui.gamecardinvoice.CardInvoiceAdapter;

public class InvoiceBrandItem extends AbstractExpandableItem<InvoiceCardDetailItem> implements MultiItemEntity {

    private String brandName;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return CardInvoiceAdapter.TYPE_INVOICE_CARD_BRAND;
    }
}
