package com.example.tinder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CA5StudentFragment extends Fragment {
    private CA5StudentFragment.FragmentCA5StudentListener listener;
    //Creating a tag
    private static final String TAG = "CA5StudentFragment";

    public interface FragmentCA5StudentListener {
        void onInputCA5StudentSent(String MinRent, String MaxRent, String MinSize, String MaxSize, String MinHouseMates, String MaxHouseMates);
    }

    private Button mButtonNext;
    private EditText mMinRent, mMaxRent, mMinSize,mMaxSize,mMinHouseMates,mMaxHouseMates;
    private FirebaseAuth mAuth;
    private DatabaseReference mStudentAccountDatabase;
    private String MinRent, MaxRent, MinSize, MaxSize, MinHouseMates, MaxHouseMates, userId;

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        final View view = inflater.inflate(R.layout.ca5_student_fragment, container, false);

        mMinRent = (EditText) view.findViewById(R.id.MinRent);
        mMaxRent = (EditText) view.findViewById(R.id.MaxRent);
        mMinSize = (EditText) view.findViewById(R.id.MinSize);
        mMaxSize = (EditText) view.findViewById(R.id.MaxSize);
        mMinHouseMates = (EditText) view.findViewById(R.id.MinHousemates);
        mMaxHouseMates = (EditText) view.findViewById(R.id.MaxHousemates);

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

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MinRent = mMinRent.getText().toString();
                MaxRent = mMaxRent.getText().toString();
                MinSize = mMinSize.getText().toString();
                MaxSize = mMaxSize.getText().toString();
                MinHouseMates = mMinHouseMates.getText().toString();
                MaxHouseMates = mMaxHouseMates.getText().toString();

                listener.onInputCA5StudentSent(MinRent,MaxRent, MinSize, MaxSize, MinHouseMates, MaxHouseMates);

                mAuth = FirebaseAuth.getInstance();
                userId = mAuth.getCurrentUser().getUid();
                mStudentAccountDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Student").child(userId);

                saveUserInformation();
            }
        });

        return view;
    }

    private void saveUserInformation() {
        Map userInfo = new HashMap();
        userInfo.put("MinRent", MinRent);
        userInfo.put("MaxRent", MaxRent);
        userInfo.put("MinSize", MinSize);
        userInfo.put("MaxSize", MaxSize);
        userInfo.put("MinHouseMates", MinHouseMates);
        userInfo.put("MaxHouseMates", MaxHouseMates);

        mStudentAccountDatabase.updateChildren(userInfo);

    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof CA5StudentFragment.FragmentCA5StudentListener){
            listener = (CA5StudentFragment.FragmentCA5StudentListener) context;
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }

}
