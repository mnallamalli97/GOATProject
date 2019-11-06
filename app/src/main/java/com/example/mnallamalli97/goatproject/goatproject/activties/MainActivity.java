package com.example.mnallamalli97.goatproject.goatproject.activties;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mnallamalli97.goatproject.R;

//this is the starting point for the app
//this is a blank activity.
//its only purpose is to be a frame for frag manager.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Since this is just a blank activity, we must push ForecastMasterFragment onto the content_frame.
        pushToFragmentManager(getFragmentManager(), R.id.content_frame, new ForecastMasterFragment(), false);

    }
}
