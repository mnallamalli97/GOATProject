package com.example.mnallamalli97.goatproject.goatproject.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import com.example.mnallamalli97.goatproject.goatproject.R;
import com.example.mnallamalli97.goatproject.goatproject.models.DarkSky;
import com.example.mnallamalli97.goatproject.goatproject.models.DarkSkyHourly;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ForcastHourlyAdapter extends RecyclerView.Adapter<ForcastHourlyViewHolder> {
    @Nullable
    public static List<DarkSkyHourly> hourlyForecast;
    private LayoutInflater inflater;
    private Context context;

    public ForcastHourlyAdapter(@Nullable List<DarkSkyHourly> hourlyForecast, Context context) {
        this.hourlyForecast = hourlyForecast;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ForcastHourlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.forcast_hour_card, parent, false);
        return new ForcastHourlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForcastHourlyViewHolder holder, int position) {
        DarkSkyHourly hour = hourlyForecast.get(position);


        String hourName = makeHour(hour.getTime(), "ha", "GMT-6:00");
        String hourNameFormatted = String.format(Locale.ENGLISH, "%s", hourName);

        // Setup high and low temps.
        Long temp = Math.round(hour.getTemperature());
        String tempFormatted = String.format(Locale.ENGLISH, "%s°", temp);

        holder.hour.setText(hourNameFormatted);
        holder.temp.setText(tempFormatted);
    }

    @Override
    public int getItemCount() {
        return (hourlyForecast != null) ? hourlyForecast.size() : 0;
    }

    public void updateForecastData(List<DarkSkyHourly> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
        updateView();
    }

    public static String makeDate(Integer epoch, String format, String timezone) {
        // Convert the epoch to a long and then create a new date with it.
        Date date = new Date(epoch.longValue() * 1000);
        // Create a new calendar using the provided timezone.
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
        // Add the new date to the calendar.
        calendar.setTime(date);
        // Return a formatted date string using SimpleDateFormat and the provided format.
        return new SimpleDateFormat(format, Locale.ENGLISH).format(calendar.getTime());

    }

    public static String makeHour(Integer epoch, String format, String timezone) {
        // Convert the epoch to a long and then create a new date with it.
        Date date = new Date(epoch.longValue() * 1000);
        // Create a new calendar using the provided timezone.
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone));
        // Add the new date to the calendar.
        calendar.setTime(date);
        // Return a formatted date string using SimpleDateFormat and the provided format.
        return new SimpleDateFormat(format, Locale.ENGLISH).format(calendar.getTime());

    }

    public void updateView() {
        notifyDataSetChanged();
    }

}