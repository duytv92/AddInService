package vn.netstars.addin.imedia.topup.datasource.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CardBrandModel implements Parcelable {
    private int id;
    private String productName;
    private String productTypeKey;
    private ArrayList<CardPriceModel> cardValues;

    protected CardBrandModel(Parcel in) {
        id = in.readInt();
        productName = in.readString();
        productTypeKey = in.readString();
        cardValues = in.createTypedArrayList(CardPriceModel.CREATOR);
    }

    public static final Creator<CardBrandModel> CREATOR = new Creator<CardBrandModel>() {
        @Override
        public CardBrandModel createFromParcel(Parcel in) {
            return new CardBrandModel(in);
        }

        @Override
        public CardBrandModel[] newArray(int size) {
            return new CardBrandModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTypeKey() {
        return productTypeKey;
    }

    public void setProductTypeKey(String productTypeKey) {
        this.productTypeKey = productTypeKey;
    }

    public ArrayList<CardPriceModel> getCardValues() {
        return cardValues;
    }

    public void setCardValues(ArrayList<CardPriceModel> cardValues) {
        this.cardValues = cardValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(productName);
        dest.writeString(productTypeKey);
        dest.writeTypedList(cardValues);
    }
}
