package com.ocpay.wallet.bean.home;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by y on 2017/11/17.
 */

public class Generalize extends BastBean implements Parcelable {

    public String imgUrl;
    public int type;
    public int resId;
    public String jumpUrl;


    public Generalize(int type, String imgUrl) {
        this.imgUrl = imgUrl;
        this.type = type;
    }

    protected Generalize(Parcel in) {
        type = in.readInt();
        imgUrl = in.readString();
        jumpUrl = in.readString();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public static final Creator<Generalize> CREATOR = new Creator<Generalize>() {
        @Override
        public Generalize createFromParcel(Parcel in) {
            return new Generalize(in);
        }

        @Override
        public Generalize[] newArray(int size) {
            return new Generalize[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(type);
        parcel.writeString(imgUrl);
        parcel.writeString(jumpUrl);
    }
}
