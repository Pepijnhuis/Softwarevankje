package com.example.tinder.CreateAccount;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tinder.R;

public class CA5StudentFragment extends Fragment {

    //Creating a tag
    private static final String TAG = "CA5StudentFragment";

    private Button mButtonNext;

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca5_student_fragment, container, false);

        mButtonNext = (Button) view.findViewById(R.id.ButtonNextCA5Student);

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
        //fr.replace(R.id.containerCreateAccountStudent,new CA3StudentFragment());
        //fr.commit();
        //}
        //}
        //});

        return view;
    }
}