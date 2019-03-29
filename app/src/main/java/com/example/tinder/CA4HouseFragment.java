package com.example.tinder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CA4HouseFragment extends Fragment {

    //Creating a tag
    private static final String TAG = "CA4HouseFragment";

    private Button mButtonNext;

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca4_house_fragment, container, false);

        mButtonNext = (Button) view.findViewById(R.id.ButtonNextCA4House);

        //mButtonNext.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //Edittext to String
                //Email = mEmail.getText().toString();
                //Password = mPassword.getText().toString();
                //listener.onInputCA1StudentSent(Email,Password);

                //next button
                //if (Email != null && Password !=null){
                    //FragmentTransaction fr = getFragmentManager().beginTransaction();
                    //fr.replace(R.id.containerCreateAccountHouse,new CA5HouseFragment());
                    //fr.commit();
                //}
            //}
        //});

        //next button
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.containerCreateAccountHouse,new CA5HouseFragment());
                fr.commit();
            }
        });
        return view;
    }
}