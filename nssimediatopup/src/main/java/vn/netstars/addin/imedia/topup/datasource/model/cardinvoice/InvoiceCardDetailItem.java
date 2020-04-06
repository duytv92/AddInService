package vn.netstars.addin.imedia.topup.datasource.model.cardinvoice;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import vn.netstars.addin.imedia.topup.ui.gamecardinvoice.CardInvoiceAdapter;

public class InvoiceCardDetailItem implements MultiItemEntity {

    private String serial;
    private String keyCard;
    private String price;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getKeyCard() {
        return keyCard;
    }

    public void setKeyCard(String keyCard) {
        this.keyCard = keyCard;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int getItemType() {
        return CardInvoiceAdapter.TYPE_INVOICE_CARD_VALUE;
    }
}
