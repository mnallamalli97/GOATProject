package com.example.mnallamalli97.goatproject.goatproject.activties;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.mnallamalli97.goatproject.goatproject.R;
import com.example.mnallamalli97.goatproject.goatproject.fragments.ForcastFragment;

//this is the starting point for the app
//this is a blank activity.
//its only purpose is to be a frame for frag manager.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Since this is just a blank activity, we must push ForecastMasterFragment onto the content_frame.
        displayFrag(getFragmentManager(), R.id.content_frame, new ForcastFragment(), false);

    }

    public static void displayFrag(FragmentManager fragmentManager, int target, Fragment fragment, Boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(target, fragment);
        if(addToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
