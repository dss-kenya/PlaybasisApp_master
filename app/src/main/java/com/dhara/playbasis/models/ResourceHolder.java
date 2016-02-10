package com.dhara.playbasis.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhara on 2/8/2016.
 */
public class ResourceHolder {
    @SerializedName("resources")
    private List<Resources> resourceList;

    public List<Resources> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resources> resourceList) {
        this.resourceList = resourceList;
    }
}
