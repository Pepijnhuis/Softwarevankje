package com.example.tinder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CA1Fragment extends Fragment {

    //Creating a tag
    private static final String TAG = "CA1Fragment";
    private EditText mEmail, mPassword;

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca1_fragment, container, false);

        mEmail = (EditText) view.findViewById(R.id.Email);
        mPassword = (EditText) view.findViewById(R.id.Password);

        String Email = mEmail.getText().toString();
        String Password = mPassword.getText().toString();

        NewAccountData.Email = Email;
        NewAccountData.Password = Password;
        //Check method if correct level 2
        return view;
    }
}
