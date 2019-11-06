package com.example.mnallamalli97.goatproject.goatproject.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import com.example.mnallamalli97.goatproject.R;

import java.util.List;
import java.util.Locale;

public class ForcastAdapter extends RecyclerView.Adapter<ForcastViewHolder> {
    @Nullable
    public static List<Datum> weeklyForecast;
    private LayoutInflater inflater;
    private Context context;

    public ForcastAdapter(@Nullable List<Datum> weeklyForecast, Context context) {
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
        Datum day = weeklyForecast.get(position);

        String dayName = convertEpochToString(day.getTime(), "EEEE", "GMT-6:00");
        String dayNameFormatted = String.format(Locale.ENGLISH, "%s", dayName);

        // Setup high and low temps.
        Long lowTemp = Math.round(day.getTemperatureMin());
        String lowTempFormatted = String.format(Locale.ENGLISH, "%s°", lowTemp);

        Long highTemp = Math.round(day.getTemperatureMax());
        String highTempFormatted = String.format(Locale.ENGLISH, "%s°", highTemp);

        String iconString = "wi_forecast_io_" + day.getIcon().replace("-", "_");
        String weatherIconResource = context.getString(getStringIdentifier(context, iconString));

        holder.day.setText(dayNameFormatted);
        holder.low.setText(lowTempFormatted);
        holder.high.setText(highTempFormatted);
    }

    @Override
    public int getItemCount() {
        return (weeklyForecast != null) ? weeklyForecast.size() : 0;
    }

    public void updateForecastData(List<Datum> weeklyForecast) {
        this.weeklyForecast = weeklyForecast;
        updateView();
    }

    public void updateView() {
        notifyDataSetChanged();
    }

}