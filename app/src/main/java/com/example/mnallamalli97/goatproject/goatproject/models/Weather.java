package com.example.mnallamalli97.goatproject.goatproject.models;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;

public class Weather {
    String name;
    String latitude;
    String longitude;


    public Weather(Activity activity) {
        setName(getStringFromSharedPrefs("city", activity));
        setLatitude(getStringFromSharedPrefs("latitude", activity));
        setLongitude(getStringFromSharedPrefs("longitude", activity));
    }

    public static String getStringFromSharedPrefs(String key, Activity activity) {
        SharedPreferences sharedPrefs = activity.getPreferences(Context.MODE_PRIVATE);

        return sharedPrefs.getString(key, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Location getLatitudeLongitude() {
        Location location = new Location("");

        location.setLatitude(Double.parseDouble(getLatitude()));
        location.setLongitude(Double.parseDouble(getLongitude()));

        return location;
    }

}
