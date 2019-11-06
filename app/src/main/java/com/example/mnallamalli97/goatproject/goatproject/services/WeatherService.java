package com.example.mnallamalli97.goatproject.goatproject.services;

import com.example.mnallamalli97.goatproject.goatproject.models.Forcast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * This is an interface necessary for Retrofit to properly work with the Darksky API.
 * https://square.github.io/retrofit/
 */
public interface WeatherService {
    @GET("{latitude},{longitude}")
    Call<Forcast> getWeather(
            @Path("latitude") Double latitude,
            @Path("longitude") Double longitude
    );
}