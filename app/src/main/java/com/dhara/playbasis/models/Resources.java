package com.dhara.playbasis.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dhara on 2/8/2016.
 */
public class Resources {
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
}
