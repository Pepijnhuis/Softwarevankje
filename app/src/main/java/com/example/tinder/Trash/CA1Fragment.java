package com.example.tinder.Trash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tinder.CreateAccount.CA3HouseFragment;
import com.example.tinder.R;

public class CA1Fragment extends Fragment {

    private FragmentCA1Listener listener;
    private EditText mEmail, mPassword;
    private Button mButtonNext;

    private String Email, Password;

    public interface FragmentCA1Listener {
        void onInputCA1Sent(String Email, String Password);
    }

    //Creating a tag
    private static final String TAG = "CA1Fragment";

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca1_fragment, container, false);

        // Find objects in layout
        mEmail = (EditText) view.findViewById(R.id.Email);
        mPassword = (EditText) view.findViewById(R.id.Password);
        mButtonNext = (Button) view.findViewById(R.id.ButtonNextCA1);

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Edittext to String
                Email = mEmail.getText().toString();
                Password = mPassword.getText().toString();
                Log.d("Debug", Email+Password);
                listener.onInputCA1Sent(Email,Password);

            }
        });

        //next button
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.containerCreateAccountHouse,new CA3HouseFragment());
                fr.commit();
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentCA1Listener){
            listener = (FragmentCA1Listener) context;
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }
}
