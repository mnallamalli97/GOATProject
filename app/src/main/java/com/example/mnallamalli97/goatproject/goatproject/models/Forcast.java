package com.example.mnallamalli97.goatproject.goatproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** models created using what the example request looks like from:
 * https://darksky.net/dev/docs#forecast-request
 * did not write this code. Got it directly from darksky.
**/

public class Forcast {

    @SerializedName("currently")
    @Expose
    private Currently currently;
    @SerializedName("daily")
    @Expose
    private Daily daily;
    @SerializedName("hourly")
    @Expose
    private Hourly hourly;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("timezone")



    public Currently getCurrently() {
        return currently;
    }

    public Daily getDaily() {
        return daily;
    }

    public Hourly getHourly() {
        return hourly;
    }


    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }


}

