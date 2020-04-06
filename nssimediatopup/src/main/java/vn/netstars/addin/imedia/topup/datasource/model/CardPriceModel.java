package vn.netstars.addin.imedia.topup.datasource.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CardPriceModel implements Parcelable {
    private int id;
    private String name;
    private int value;

    protected CardPriceModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        value = in.readInt();
    }

    public static final Creator<CardPriceModel> CREATOR = new Creator<CardPriceModel>() {
        @Override
        public CardPriceModel createFromParcel(Parcel in) {
            return new CardPriceModel(in);
        }

        @Override
        public CardPriceModel[] newArray(int size) {
            return new CardPriceModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(value);
    }
}
