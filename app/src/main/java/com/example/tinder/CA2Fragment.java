package com.example.tinder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CA2Fragment extends Fragment {

    //Creating a tag
    private static final String TAG = "CA2Fragment";


    private RadioGroup radioGroup;
    private RadioButton RadioLookingForHouse, RadioLookingForStudent;
    private String LookingFor; //0=looking for house, 1=looking for housemate


    //to communicate between fragments
    private FragmentCA2listener listener;

    //to communicate between fragments
    public interface FragmentCA2listener{
        void onInputCA2Sent(String input);
    }

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca2_fragment, container, false);

        //Radiogroup for choosing house or housemate
        radioGroup = (RadioGroup) view.findViewById(R.id.RadioLookingFor);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.RadioLookingForHouse) {
                    Toast.makeText(getActivity(), "choice: Looking for House",
                            Toast.LENGTH_SHORT).show();
                    LookingFor = "House";
                    listener.onInputCA2Sent(LookingFor);
                } else {
                    Toast.makeText(getActivity(), "choice: Looking for Housemate",
                            Toast.LENGTH_SHORT).show();
                    LookingFor = "Student";
                    listener.onInputCA2Sent(LookingFor);
                }
            }
        });
        return view;
    }

    //attaching fragment
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCA2listener) {
            listener = (FragmentCA2listener) context;
        }
    }

    //turning off listener when activity is turned off
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}