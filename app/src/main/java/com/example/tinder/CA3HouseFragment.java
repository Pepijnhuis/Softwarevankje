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
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CA3HouseFragment extends Fragment {


    private EditText mNameHouse, mRent, mSize, mNumberHousemates, mAboutMe;


    private Button mBack, mNext;

    private FirebaseAuth mAuth;
    private DatabaseReference mStudentAccountDatabase;
    private String NameHouse, Rent, Size, NumberHousemates,Aboutme, userId;

    //Creating a tag
    private static final String TAG = "CA3HouseFragment";

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca3_house_fragment, container, false);

        mNameHouse = (EditText) view.findViewById(R.id.NameHouse);
        mRent = (EditText) view.findViewById(R.id.Rent);
        mSize = (EditText) view.findViewById(R.id.Size);
        mNumberHousemates = (EditText) view.findViewById(R.id.NumberHousemates);
        mAboutMe = (EditText) view.findViewById(R.id.AboutMe);
        mNext = (Button) view.findViewById(R.id.ButtonNextCA3House);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameHouse = mNameHouse.getText().toString();
                Rent = mRent.getText().toString();
                Size = mSize.getText().toString();
                NumberHousemates = mNumberHousemates.getText().toString();
                Aboutme = mAboutMe.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                userId = mAuth.getCurrentUser().getUid();
                mStudentAccountDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Huis").child(userId);
                saveUserinformation();
        }
        });
        return view;
    }


    private void saveUserinformation() {
        Map userInfo = new HashMap();
        userInfo.put("Name", NameHouse);
        userInfo.put("Rent", Rent);
        userInfo.put("Size",Size);
        userInfo.put("NumeberHousemates", NumberHousemates);
        userInfo.put("AboutUs", Aboutme);
        mStudentAccountDatabase.updateChildren(userInfo);

    }
}
