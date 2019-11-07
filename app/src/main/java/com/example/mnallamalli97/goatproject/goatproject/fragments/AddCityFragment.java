package com.example.mnallamalli97.goatproject.goatproject.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.example.mnallamalli97.goatproject.R;

public class AddCityFragment extends DialogFragment {
    Context context;

    public static AddCityFragment newInstance() {
        Bundle args = new Bundle();

        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Please set a location").setMessage("Example: Paris, France or 95864");

        // Set up the input
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

//        builder.setPositiveButton("ADD CITY",  new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String location = input.getText().toString();
//                SharedPreferencesHelper.CreateCityInSharedPrefs(getActivity(), location);
//                pushToFragmentManager(getFragmentManager(), R.id.content_frame, new ForcastFragment(), false);
//            }
//        });


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
    public static void pushToFragmentManager(FragmentManager fragmentManager, int target, Fragment fragment, Boolean addToBackStack) {
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
