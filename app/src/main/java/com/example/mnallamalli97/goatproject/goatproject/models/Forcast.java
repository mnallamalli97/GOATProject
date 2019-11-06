package com.example.mnallamalli97.goatproject.goatproject.models;




import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** models created using what the example request looks like from:
  * https://darksky.net/dev/docs#forecast-request
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
    @Expose
    private String timezone;

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}

