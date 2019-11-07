package com.example.mnallamalli97.goatproject.goatproject.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * DID NOT WRITE THIS CODE. GOT IT DIRECTLY FROM DARKSKY
 */

public class DarkSkyHourly {

    @SerializedName("apparentTemperature")
    @Expose
    private Double apparentTemperature;

    @SerializedName("cloudCover")
    @Expose
    private Double cloudCover;
    @SerializedName("dewPoint")
    @Expose
    private Double dewPoint;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("ozone")
    @Expose
    private Double ozone;
    @SerializedName("precipIntensity")
    @Expose
    private Double precipIntensity;
    @SerializedName("precipProbability")
    @Expose
    private Double precipProbability;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("temperature")
    @Expose
    private Double temperature;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("visibility")
    @Expose
    private Double visibility;
    @SerializedName("windBearing")
    @Expose
    private Integer windBearing;
    @SerializedName("windSpeed")
    @Expose
    private Double windSpeed;
    @SerializedName("precipType")
    @Expose
    private String precipType;


    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public Double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

}
