package com.example.mnallamalli97.goatproject.goatproject.fragments;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mnallamalli97.goatproject.goatproject.R;
import com.example.mnallamalli97.goatproject.goatproject.adapter.ForcastAdapter;
import com.example.mnallamalli97.goatproject.goatproject.models.Forcast;
import com.example.mnallamalli97.goatproject.goatproject.models.Weather;
import com.example.mnallamalli97.goatproject.goatproject.services.GetWeather;
import com.example.mnallamalli97.goatproject.goatproject.services.ItemClickSupport;

public class ForcastFragment extends Fragment implements View.OnClickListener {
    public ForcastAdapter adapter;
    private RecyclerView recyclerView;
    //private View view;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Only inflate our view and do the setup functions if the view is null. This prevents pop in of data and unnecessary API calls.
        //if (view == null) {
            /*this*/ View view = inflater.inflate(R.layout.forcast_fragment, container, false);

            // Setup the adapter so that it can be modified later asynchronously.
            this.adapter = new ForcastAdapter(null, view.getContext());
        // Set up the recyclerview.
        setupRecyclerView(view);

        //}

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Fetch the location data and setup all weather data on this fragment.
        initializeWeatherData();
    }

    /**
     * If the user has provided a location, this function calls the Darksky API and populates all data accordingly.
     * Otherwise, a DialogFragment is shown that allows them to do so.
     */
    private void initializeWeatherData() {
        Weather location = new Weather(getActivity());

        // Make sure the user has put in a location.
        if (location.getName() != null) {
            // Fetch the current forecast, which updates current conditions and weekly forecast.
            GetWeather.getWeatherData(location.getLatitudeLongitude(), adapter, getString(R.string.dark_sky_api), this);

            // Set the text on the location label.
            TextView locationLabel = (TextView) getView().findViewById(R.id.text_location_name);
            locationLabel.setText(location.getName());

            // If they haven't, ask them to put in a location.
        } else {
            ManualEntry addCityDialogFragment = new ManualEntry().newInstance();

            if (!addCityDialogFragment.isActive()) {
                addCityDialogFragment.show(getFragmentManager(), "fragment_add_city");
            }
        }
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                12);
    }



    /**
     * Fetches and configures the RecyclerView to be displayed.
     */
    private void setupRecyclerView(View view) {
        // Get the recyclerview so that we can set it up.
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_week_forecast);

        // Set the adapter for the recyclerview. In this case, we are using null for our weather data because it will be fetched asynchronously later on.
        recyclerView.setAdapter(adapter);

        // Use this ItemClickSupport found at the following url to setup click support.
        // https://gist.github.com/nesquena/231e356f372f214c4fe6
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // When the user taps an item, a new instance of ForecastDetailFragment is provided and the position they selected is passed.
                        pushToFragmentManager(getFragmentManager(), R.id.content_frame, ForcastDayFragment.newInstance(adapter.weeklyForecast, position), true);
                    }
                }
        );

        // Setup the layout as Linear.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * This populates all the current conditions on the fragment.
     * @param weatherData This is used to populate all of our labels.
     */
    public void updateCurrentConditions(Forcast weatherData) {
        // If the view doesn't exist, an error will occur because we are calling it below. Return to prevent this.
        if (getView() == null || !isAdded()) {
            return;
        }

        /**
         * Fetch all the text views to populate them below.
         */
        TextView currentTempLabel           = (TextView) getView().findViewById(R.id.text_current_temp);
        TextView currentConditionLabel      = (TextView)  getView().findViewById(R.id.text_current_condition);
        TextView currentPrecipitationLabel  = (TextView)  getView().findViewById(R.id.text_current_precipitation);
        TextView currentWindLabel           = (TextView)  getView().findViewById(R.id.text_wind_speed);
        TextView todayHighTempLabel         = (TextView)  getView().findViewById(R.id.text_today_high);
        TextView todayLowTempLabel          = (TextView)  getView().findViewById(R.id.text_today_low);

        /**
         * Fetch all the data.
         */
        String summary      = weatherData.getCurrently().getSummary();
        long currentTemp    = Math.round(weatherData.getCurrently().getTemperature());
        long precipitation  = Math.round(weatherData.getCurrently().getPrecipProbability());
        long lowTemp        = Math.round(weatherData.getDaily().getData().get(0).getTemperatureMin());
        long highTemp       = Math.round(weatherData.getDaily().getData().get(0).getTemperatureMax());
        long windSpeed      = Math.round(weatherData.getCurrently().getWindSpeed());



        /**
         *  Populate all the text views.
         */
        currentConditionLabel.setText(summary);
        currentPrecipitationLabel.setText(getString(R.string.weather_percent, precipitation));
        currentTempLabel.setText(getString(R.string.weather_temperature, currentTemp));
        todayHighTempLabel.setText(getString(R.string.weather_temperature, highTemp));
        todayLowTempLabel.setText(getString(R.string.weather_temperature, lowTemp));
        currentWindLabel.setText(getString(R.string.weather_wind, windSpeed));

    }

    /**
     * Pushes a new fragment on to the fragment manager stack.
     *
     * @param fragmentManager Pass in the FragmentManager so that it can do the necessary work.
     * @param target Id of the content frame to be targeted.
     * @param fragment Fragment to be pushed onto the stack.
     * @param addToBackStack Does this needed to be added to the back stack for navigation purposes?
     */
    public static void pushToFragmentManager(FragmentManager fragmentManager, int target, Fragment fragment, Boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction().add(target, fragment);

        if(addToBackStack) transaction.addToBackStack(null);

        transaction.commit();
    }


    @Override
    public void onClick(View v) {

    }
}
