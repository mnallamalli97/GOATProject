package com.example.mnallamalli97.goatproject.goatproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hourly {

    @SerializedName("data")
    @Expose
    private List<DarkSky> data = null;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("summary")
    @Expose
    private String summary;

    public List<DarkSky> getData() {
        return data;
    }

    public void setData(List<DarkSky> data) {
        this.data = data;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}
