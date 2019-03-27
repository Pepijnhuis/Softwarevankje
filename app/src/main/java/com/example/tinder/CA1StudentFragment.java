package com.example.tinder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CA1StudentFragment extends Fragment {

    private FragmentCA1Listener listener;
    private EditText mEmail, mPassword;
    private Button mButtonNext;

    private String Email, Password;

    public interface FragmentCA1Listener {
        void onInputCA1Sent(String Email, String Password);
    }

    //Creating a tag
    private static final String TAG = "CA1StudentFragment";

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca1__student_fragment, container, false);

        // Find objects in layout
        mEmail = (EditText) view.findViewById(R.id.Email);
        mPassword = (EditText) view.findViewById(R.id.Password);
        mButtonNext = (Button) view.findViewById(R.id.ButtonNextCA1Student);

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Edittext to String
                Email = mEmail.getText().toString();
                Password = mPassword.getText().toString();
                listener.onInputCA1Sent(Email,Password);

            }
        });

        //next buttons
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.containerCreateAccountStudent,new CA3StudentFragment());
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
