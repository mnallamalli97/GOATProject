package com.example.mnallamalli97.goatproject.goatproject.adapter;

import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.example.mnallamalli97.goatproject.goatproject.R;

import org.w3c.dom.Text;

public class ForcastHourlyViewHolder extends RecyclerView.ViewHolder{
    TextView hour;
    TextView temp;

    public ForcastHourlyViewHolder(View itemView) {
        super(itemView);

        hour = (TextView) itemView.findViewById(R.id.tv_weekly_forecast_hour);
        temp = (TextView) itemView.findViewById(R.id.tv_hour_forecast_temp);
    }
}
