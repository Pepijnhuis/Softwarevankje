package com.example.tinder.CreateAccount;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tinder.R;

public class CA6HouseFragment extends Fragment {

    //Creating a tag
    private static final String TAG = "CA6StudentFragment";


    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        final View view = inflater.inflate(R.layout.ca6_house_fragment, container, false);

        return view;
    }
}