package com.example.tinder;

import android.content.Context;
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
import android.widget.Toast;

public class CA1HouseFragment extends Fragment {

    private FragmentCA1HouseListener listener;
    private EditText mEmail, mPassword;
    private Button mButtonNext, mSkip;

    private String Email, Password;

    public interface FragmentCA1HouseListener {
        void onInputCA1HouseSent(String Email, String Password);
    }

    //Creating a tag
    private static final String TAG = "CA1HouseFragment";

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca1_house_fragment, container, false);

        // Find objects in layout
        mEmail = (EditText) view.findViewById(R.id.Email);
        mPassword = (EditText) view.findViewById(R.id.Password);
        mButtonNext = (Button) view.findViewById(R.id.ButtonNextCA1House);
        mSkip = (Button) view.findViewById(R.id.ButtonBackCA1House);

        mSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.containerCreateAccountHouse,new CA3HouseFragment());
                fr.commit();
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Edittext to String
                Email = mEmail.getText().toString();
                Password = mPassword.getText().toString();

                //invalid email
                if (Email.matches("") ) {
                    // Show Error on edittext
                    mEmail.setError("Invalid email");
                    Log.d("Debug", "no email");

                    //invalid email and password
                    if (Password.matches("")) {
                        // Show Error on edittext
                        mPassword.setError("Invalid password");
                        Log.d("Debug", "no password");}
                    return;
                }

                //invalid password
                if (Password.matches("")) {
                    // Show Error on edittext
                    mPassword.setError("Invalid password");
                    Log.d("Debug", "no password");
                    return;
                }

                //valid email and password
                else{
                    //send to activity
                    listener.onInputCA1HouseSent(Email,Password);
                    //go to next fragment
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.containerCreateAccountHouse,new CA3HouseFragment());
                    fr.commit();
                }
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentCA1HouseListener){
            listener = (FragmentCA1HouseListener) context;
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }
}
