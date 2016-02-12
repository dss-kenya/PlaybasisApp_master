package com.dhara.playbasis.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dhara on 2/8/2016.
 */
public class Resources implements Parcelable{
    @SerializedName("public_id")
    private String publicId;
    @SerializedName("version")
    private double version;
    @SerializedName("format")
    private String fileExt;

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public Resources(){}

    public Resources(Parcel in) {
        this.publicId = in.readString();
        this.version = in.readDouble();
        this.fileExt = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.publicId);
        parcel.writeDouble(this.version);
        parcel.writeString(this.fileExt);
    }

    public final static Parcelable.Creator<Resources> CREATOR = new Parcelable.Creator<Resources>(){
        @Override
        public Resources createFromParcel(Parcel parcel) {
            return new Resources(parcel);
        }

        @Override
        public Resources[] newArray(int size) {
            return new Resources[size];
        }
    };
}
