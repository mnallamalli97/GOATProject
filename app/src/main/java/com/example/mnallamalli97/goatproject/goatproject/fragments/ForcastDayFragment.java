package com.example.mnallamalli97.goatproject.goatproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mnallamalli97.goatproject.goatproject.R;
import com.example.mnallamalli97.goatproject.goatproject.adapter.ForcastHourlyAdapter;
import com.example.mnallamalli97.goatproject.goatproject.models.DarkSky;
import com.example.mnallamalli97.goatproject.goatproject.models.Weather;
import com.example.mnallamalli97.goatproject.goatproject.services.GetWeather;

import android.app.Fragment;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ForcastDayFragment extends Fragment{
    View view;
    private RecyclerView recyclerView;
    public ForcastHourlyAdapter adapter;

    /**
     * Creates a new instance of ForecastDetailFragment. This is primarily used so that we can pass the index (int selectedDay).
     * @param forecast All forecast data. This is narrowed down by using selectedDay.
     * @param selectedDay Used to show the day selected by the user.
     * @return ForecastDetailFragment
     */
    public static ForcastDayFragment newInstance(List<DarkSky> forecast, int selectedDay) {

        // Create a bundle with all weather data so that we can access it when the view is being created below.
        Bundle args = createBundle(forecast, selectedDay);

        // Create the new ForecastDetailFragment, set it's argument and then return it.
        ForcastDayFragment fragment = new ForcastDayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Setup the view so that we can access it's components.
        if(view == null) this.view = inflater.inflate(R.layout.forcast_fragment_hourly, container, false);

        this.adapter = new ForcastHourlyAdapter(null, view.getContext());

        setupRecyclerView();
        // Populate all the text views on the fragment with the correct data.
        populateWeatherData();

        return view;
    }

    /**
     * Pulls all arguments and then pouplates the text views with the correct data.
     */
    private void populateWeatherData() {

        // Get all of our arguments that were populated earlier.
        Bundle args = getArguments();

        Weather location = new Weather(getActivity());
        // Fetch the current forecast, which updates current conditions and weekly forecast.
        GetWeather.getHourlyWeatherData(location.getLatitudeLongitude(), adapter, getString(R.string.dark_sky_api));

        /**
         * Setup all of our text views.
         */
        TextView detailDate         = (TextView) view.findViewById(R.id.text_detail_date);
        TextView condition          = (TextView) view.findViewById(R.id.text_detail_condition);
        TextView lowTemp            = (TextView) view.findViewById(R.id.text_detail_low);
        TextView highTemp           = (TextView) view.findViewById(R.id.text_detail_high);

        /**
         * Format all of our data correctly.
         */
        String dateString           = makeDay(args.getInt("time"), "EEEE", "GMT-6:00");
        String conditionString      = args.getString("condition");
        long lowTempLong            = Math.round(Double.valueOf(args.getString("low")));
        long highTempLong           = Math.round(Double.valueOf(args.getString("high")));




        /**
         * Populate data from the bundle into the text views.
         */
        detailDate.setText(dateString);
        condition.setText(conditionString);
        lowTemp.setText(getString(R.string.weather_temperature, lowTempLong));
        highTemp.setText(getString(R.string.weather_temperature, highTempLong));
    }

    /**
     * Fetches and configures the RecyclerView to be displayed.
     */
    private void setupRecyclerView() {
        // Get the recyclerview so that we can set it up.
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_hour_forecast);
        recyclerView.setAdapter(adapter);

        // Setup the layout as Linear.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

    }

    /**
     * A helper to create our bundle that is used within onCreateView.
     * @param forecast
     * @param selectedDay
     * @return Bundle
     */

    private static Bundle createBundle(List<DarkSky> forecast, int selectedDay) {
        // Create a bundle so that we can access it when the view is being created below.
        Bundle bundle = new Bundle();

        // Filter out the selected day from the forecast.
        DarkSky dailyForecast = forecast.get(selectedDay);

        /**
         * Build out our bundle with weather data.
         */
        bundle.putInt("time", dailyForecast.getTime());
        bundle.putString("condition", dailyForecast.getSummary());
        bundle.putString("low", String.valueOf(dailyForecast.getTemperatureMin()));
        bundle.putString("high", String.valueOf(dailyForecast.getTemperatureMax()));

        return bundle;
    }

    public static String makeDay(Integer epoch, String format, String timezone) {
        // Convert the epoch to a long and then create a new date with it.
        Date date = new Date(epoch.longValue() * 1000);

        // Create a new calendar using the provided timezone.
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));

        // Add the new date to the calendar.
        calendar.setTime(date);

        // Return a formatted date string using SimpleDateFormat and the provided format.
        return new SimpleDateFormat(format, Locale.ENGLISH).format(calendar.getTime());

    }
}

