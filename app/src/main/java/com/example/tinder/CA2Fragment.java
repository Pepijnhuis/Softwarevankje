package com.example.tinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CA2Fragment extends Fragment {

    //Creating a tag
    private static final String TAG = "CA2Fragment";

    private int LookingFor;

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca2_fragment, container, false);

        //Looking For House button
        public void setLookingForHouse(View view) {
            LookingFor = 0;
            return;
        }

        //Looking For House button
        public void setLookingForStudent(View view) {
            LookingFor = 1;
            return;
        }


        return view;
    }
}