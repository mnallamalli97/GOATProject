package com.example.mnallamalli97.goatproject.goatproject.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.example.mnallamalli97.goatproject.goatproject.R;

import java.io.IOException;

public class ManualEntry extends DialogFragment {
    Context context;

    public static ManualEntry newInstance() {
        Bundle args = new Bundle();
        ManualEntry fragment = new ManualEntry();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Please set a location. Enter Zipcode or city name");

        // Set up the input
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("ADD CITY",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String providedLocation = input.getText().toString();
                try {
                    Geocoder geocoder = new Geocoder(getActivity());

                    Address location = geocoder.getFromLocationName(providedLocation, 1).get(0);
                    if (location == null){
                        return;
                    }
                    SharedPreferences sharedPrefs = getActivity().getPreferences(Context.MODE_PRIVATE);
                    sharedPrefs
                            .edit()
                            .putString("city", location.getAddressLine(0))
                            .apply();

                    sharedPrefs
                            .edit()
                            .putString("latitude", String.valueOf(location.getLatitude()))
                            .apply();

                    sharedPrefs
                            .edit()
                            .putString("longitude", String.valueOf(location.getLongitude()))
                            .apply();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                displayFrag(getFragmentManager(), R.id.content_frame, new ForcastFragment(), false);
            }
        });

        return builder.create();
    }

    /**
     * Pushes a new fragment on to the fragment manager stack.
     *
     * @param fragmentManager Pass in the FragmentManager so that it can do the necessary work.
     * @param target Id of the content frame to be targeted.
     * @param fragment Fragment to be pushed onto the stack.
     * @param addToBackStack Does this needed to be added to the back stack for navigation purposes?
     */
    public static void displayFrag(FragmentManager fragmentManager, int target, Fragment fragment, Boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction().replace(target, fragment);

        if(addToBackStack) transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = getActivity();
    }

    public boolean isActive() {
        return isAdded() && !isDetached() && !isRemoving();
    }
}
