package com.example.tinder.CreateAccount;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;

public class CA5HouseFragment extends Fragment {

    //Creating a tag
    private static final String TAG = "CA5HouseFragment";

    private Button mButtonNext;
    private EditText mMinAge;
    private EditText mMaxAge;
    private RadioGroup mRadioButtonMale, mRadioGroupBscMsc, mRadioGroupMaleFemale;
    private CheckBox mCheckboxMale, mCheckboxFemale,mCheckboxBSC,mCheckboxMSC;
    private String CheckboxMale, CheckboxFemale,CheckboxBsc, CheckboxMsc;

    private String RadioGroupBscMsc, RadioGroupMaleFemale, MinAge, MaxAge, userId;
    private FirebaseAuth mAuth;
    private DatabaseReference mStudentAccountDatabase;

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        final View view = inflater.inflate(R.layout.ca5_house_fragment, container, false);


        mCheckboxMale = (CheckBox) view.findViewById(R.id.MaleYes2);
        mCheckboxFemale = (CheckBox) view.findViewById(R.id.FemaleYes);
        mCheckboxBSC = (CheckBox) view.findViewById(R.id.BscYes);
        mCheckboxMSC = (CheckBox) view.findViewById(R.id.MscYes);

        mMinAge = (EditText) view.findViewById(R.id.MaxAge);
        mMaxAge = (EditText) view.findViewById(R.id.MinAge);

        //next button
        mButtonNext = (Button) view.findViewById(R.id.ButtonNextCA5House);


        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MinAge = mMinAge.getText().toString();
                MaxAge = mMaxAge.getText().toString();
                if(mCheckboxFemale.isChecked()){
                    CheckboxFemale = "Female";
                    Log.d("Debug", "Female Checked");
                }
                if(mCheckboxMale.isChecked()){
                    CheckboxMale = "Male";
                    Log.d("Debug", "Male Checked");
                }

                if(mCheckboxBSC.isChecked()){
                    CheckboxBsc = "Bsc";
                    Log.d("Debug", "Bsc Checked");
                }

                if(mCheckboxMSC.isChecked()){
                    CheckboxMsc = "Msc";
                    Log.d("Debug", "Msc Checked");
                }
                mAuth = FirebaseAuth.getInstance();
                userId = mAuth.getCurrentUser().getUid();
                mStudentAccountDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

                saveUserInformation();

                //next fragment
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.containerCreateAccountHouse,new CA6HouseFragment());
                fr.commit();
            }
        });

        return view;
    }


    private void saveUserInformation() {
        Map userInfo = new HashMap();
        userInfo.put("MinAge", MinAge);
        userInfo.put("MaxAge", MaxAge);
        userInfo.put("MaleYes", CheckboxMale);
        userInfo.put("FemaleYes", CheckboxFemale);
        userInfo.put("BscYes", CheckboxBsc);
        userInfo.put("MscYes", CheckboxMsc);
        //Log.d("Debug", CheckboxMale);
        mStudentAccountDatabase.updateChildren(userInfo);

    }
}