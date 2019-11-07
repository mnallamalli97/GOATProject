package com.example.mnallamalli97.goatproject.goatproject.adapter;

import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.example.mnallamalli97.goatproject.goatproject.R;

import org.w3c.dom.Text;

public class ForcastViewHolder extends RecyclerView.ViewHolder{
    TextView day;
    TextView low;
    TextView high;

    public ForcastViewHolder(View itemView) {
        super(itemView);

        day = (TextView) itemView.findViewById(R.id.tv_weekly_forecast_day);
        low = (TextView) itemView.findViewById(R.id.tv_weekly_forecast_low);
        high = (TextView) itemView.findViewById(R.id.tv_weekly_forecast_high);
    }
}
