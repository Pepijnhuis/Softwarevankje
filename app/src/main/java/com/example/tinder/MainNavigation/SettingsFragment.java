package com.example.tinder.MainNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tinder.LoginActivity;
import com.example.tinder.R;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment {

    //Creating a tag

    private FirebaseAuth mAuth;
    private static final String TAG = "SettingsFragment";

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();

        Button mbutton = (Button) view.findViewById(R.id.logoutuser);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(),  LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                return;
            }
        });
        return view;
    }
}
