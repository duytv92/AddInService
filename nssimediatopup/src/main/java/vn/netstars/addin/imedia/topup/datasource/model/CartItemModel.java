package vn.netstars.addin.imedia.topup.datasource.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CartItemModel implements Parcelable {
    private int id;
    private String cardType;
    private String cardStringValue;
    private int cardPriceValue;
    private int quantity;

    public CartItemModel() {}

    protected CartItemModel(Parcel in) {
        id = in.readInt();
        cardType = in.readString();
        cardStringValue = in.readString();
        cardPriceValue = in.readInt();
        quantity = in.readInt();
    }

    public static final Creator<CartItemModel> CREATOR = new Creator<CartItemModel>() {
        @Override
        public CartItemModel createFromParcel(Parcel in) {
            return new CartItemModel(in);
        }

        @Override
        public CartItemModel[] newArray(int size) {
            return new CartItemModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardStringValue() {
        return cardStringValue;
    }

    public void setCardStringValue(String cardStringValue) {
        this.cardStringValue = cardStringValue;
    }

    public int getCardPriceValue() {
        return cardPriceValue;
    }

    public void setCardPriceValue(int cardPriceValue) {
        this.cardPriceValue = cardPriceValue;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(cardType);
        dest.writeString(cardStringValue);
        dest.writeInt(cardPriceValue);
        dest.writeInt(quantity);
    }

}
