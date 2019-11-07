package com.example.mnallamalli97.goatproject.goatproject.services;

import android.location.Location;
import android.util.Log;

import com.example.mnallamalli97.goatproject.goatproject.adapter.ForcastAdapter;
import com.example.mnallamalli97.goatproject.goatproject.adapter.ForcastHourlyAdapter;
import com.example.mnallamalli97.goatproject.goatproject.fragments.ForcastFragment;
import com.example.mnallamalli97.goatproject.goatproject.models.DarkSky;
import com.example.mnallamalli97.goatproject.goatproject.models.DarkSkyHourly;
import com.example.mnallamalli97.goatproject.goatproject.models.Forcast;

import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * THIS IS A TEMPLATE CLASS GIVEN BY RETROFIT. THE getClient FUNCTION WAS NOT WRITTEN BY ME.
 * I WROTE THE getWeatherData AND getHourlyWeatherData FUNCTION.
 */


public class GetWeather {
    private static final String BASE_URL = "https://api.darksky.net/forecast/";
    private static Retrofit retrofit = null;

    /**
     * Uses retrofit to call the Darksky api using the provided api key.
     * @param apiKey Needed to access the Darksky api.
     * @return WeatherService
     */
    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static WeatherService getWeatherService(String apiKey) {
        return getClient(BASE_URL+apiKey+'/').create(WeatherService.class);
    }

    /**
     * Pulls weather data from the Darksky API using the provided location. On success it updates the adapter and forecastMasterFragment.
     * @param longitude Used to pull the weather data for this particular location.
     * @param latitude Used to pull the weather data for this particular location.
     * @param adapter Used to update the adapter of the RecyclerView for forecastMasterFragment.
     * @param apiKey Needed to access the Darksky api.
     * @param forcastFragment Used to update the current conditions on this fragment.
     */
    public static void getWeatherData(Location location, final ForcastAdapter adapter, String apiKey, final ForcastFragment forcastFragment) {
        WeatherService api = GetWeather.getWeatherService(apiKey);

        Log.i("API", api.getWeather(location.getLatitude(), location.getLongitude()).request().url().toString());


        api.getWeather(location.getLatitude(), location.getLongitude()).enqueue(new Callback<Forcast>() {
            @Override
            public void onResponse(Call<Forcast> call, Response<Forcast> response) {
                if (response.isSuccessful()) {
                    List<DarkSky> dailyData = response.body().getDaily().getData();

                    // Update the forecast data, but return a new list that does not have today in it.
                    adapter.updateForecastData(dailyData.subList(1, dailyData.size()));

                    // Update the current conditions views.
                    forcastFragment.updateCurrentConditions(response.body());
                } else {
                    Log.e("rest error", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Forcast> call, Throwable t) {
                Log.d("Weather API", "error loading from API");
                Log.d("Weather API", t.getMessage());
            }
        });
    }

    /**
     * Pulls weather data from the Darksky API using the provided location. On success it updates the adapter and forecastMasterFragment.
     * @param longitude Used to pull the weather data for this particular location.
     * @param latitude Used to pull the weather data for this particular location.
     * @param adapter Used to update the adapter of the RecyclerView for forecastMasterFragment.
     * @param apiKey Needed to access the Darksky api.
     */
    public static void getHourlyWeatherData(Location location, final ForcastHourlyAdapter adapter, String apiKey) {
        WeatherService api = GetWeather.getWeatherService(apiKey);

        Log.i("API", api.getWeather(location.getLatitude(), location.getLongitude()).request().url().toString());


        api.getWeather(location.getLatitude(), location.getLongitude()).enqueue(new Callback<Forcast>() {
            @Override
            public void onResponse(Call<Forcast> call, Response<Forcast> response) {
                if (response.isSuccessful()) {
                    List<DarkSkyHourly> hourlyData = response.body().getHourly().getData();

                    // Update the forecast data, but return a new list that does not have today in it.
                    adapter.updateForecastData(hourlyData.subList(1, hourlyData.size()));

                } else {
                    Log.e("rest error", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Forcast> call, Throwable t) {
                Log.d("Weather API", "error loading from API");
                Log.d("Weather API", t.getMessage());
            }
        });
    }
}