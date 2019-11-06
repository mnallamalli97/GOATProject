package com.example.mnallamalli97.goatproject.goatproject.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import com.example.mnallamalli97.goatproject.R;
import com.example.mnallamalli97.goatproject.goatproject.models.DarkSky;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ForcastAdapter extends RecyclerView.Adapter<ForcastViewHolder> {
    @Nullable
    public static List<DarkSky> weeklyForecast;
    private LayoutInflater inflater;
    private Context context;

    public ForcastAdapter(@Nullable List<DarkSky> weeklyForecast, Context context) {
        this.weeklyForecast = weeklyForecast;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ForcastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.forcast_day_card, parent, false);
        return new ForcastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForcastViewHolder holder, int position) {
        DarkSky day = weeklyForecast.get(position);

        String dayName = convertEpochToString(day.getTime(), "EEEE", "GMT-6:00");
        String dayNameFormatted = String.format(Locale.ENGLISH, "%s", dayName);

        // Setup high and low temps.
        Long lowTemp = Math.round(day.getTemperatureMin());
        String lowTempFormatted = String.format(Locale.ENGLISH, "%s°", lowTemp);

        Long highTemp = Math.round(day.getTemperatureMax());
        String highTempFormatted = String.format(Locale.ENGLISH, "%s°", highTemp);

        holder.day.setText(dayNameFormatted);
        holder.low.setText(lowTempFormatted);
        holder.high.setText(highTempFormatted);
    }

    @Override
    public int getItemCount() {
        return (weeklyForecast != null) ? weeklyForecast.size() : 0;
    }

    public void updateForecastData(List<DarkSky> weeklyForecast) {
        this.weeklyForecast = weeklyForecast;
        updateView();
    }

    public static String convertEpochToString(Integer epoch, String format, String timezone) {
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