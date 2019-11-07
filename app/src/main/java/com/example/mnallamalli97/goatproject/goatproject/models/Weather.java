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
        SharedPreferences sharedPrefs = activity.getPreferences(Context.MODE_PRIVATE);

        setName(sharedPrefs.getString("city", null));
        setLatitude(sharedPrefs.getString("latitude", null));
        setLongitude(sharedPrefs.getString("longitude", null));
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
