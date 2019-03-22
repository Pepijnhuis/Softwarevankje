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
import android.widget.RadioGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.jar.Attributes;

public class CA3StudentFragment extends Fragment {
    private CA3StudentFragment.FragmentCA3StudentListener listener;
    //Creating a tag
    private static final String TAG = "CA3StudentFragment";

    public interface FragmentCA3StudentListener {
        void onInputCA3StudentSent(String NameStudent,String AdressStudent, String School, String Study, String Hobby1, String Hobby2, String Hobby3, String AboutMe);
    }



    private EditText mNameField, mAdressField, mDayField, mMonthField, mYearField, mSchoolField,
            mStudyField, mHobby1Field, mHobby2Field, mHobby3Field, mAboutMeField;

    private RadioGroup mRadioGroupBscMsc, mRadioGroupMaleFemale;

    private Button mBack, mNext, mConfirm;

    private FirebaseAuth mAuth;
    private DatabaseReference mStudentAccountDatabase;
    private String NameStudent, AdressStudent, School, Study, Hobby1, Hobby2, Hobby3, AboutMe;
    private Integer Day, Month, Year;


    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.ca3_student_fragment, container, false);


        mNameField = (EditText) view.findViewById(R.id.NameStudent);
        mAdressField = (EditText) view.findViewById(R.id.AddressHouse);
        mDayField = (EditText) view.findViewById(R.id.BirthdayDay);
        mMonthField = (EditText) view.findViewById(R.id.BirthdayMonth);
        mYearField = (EditText) view.findViewById(R.id.BirthdayYear);
        mSchoolField = (EditText) view.findViewById(R.id.SchoolStudent);
        mStudyField = (EditText) view.findViewById(R.id.StudyStudent);
        mHobby1Field = (EditText) view.findViewById(R.id.Hobby1);
        mHobby2Field = (EditText) view.findViewById(R.id.Hobby2);
        mHobby3Field = (EditText) view.findViewById(R.id.Hobby3);
        mAboutMeField = (EditText) view.findViewById(R.id.AboutMe);
        
        mNext = (Button) view.findViewById(R.id.ButtonBackC3Student);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameStudent = mNameField.getText().toString();
                AdressStudent = mAdressField.getText().toString();
                School = mSchoolField.getText().toString();
                Study = mStudyField.getText().toString();
                Hobby1 = mHobby1Field.getText().toString();
                Hobby2 = mHobby2Field.getText().toString();
                Hobby3 = mHobby3Field.getText().toString();
                AboutMe = mAboutMeField.getText().toString();
                listener.onInputCA3StudentSent(NameStudent,AdressStudent,School, Study, Hobby1, Hobby2, Hobby3, AboutMe);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof CA3StudentFragment.FragmentCA3StudentListener){
            listener = (CA3StudentFragment.FragmentCA3StudentListener) context;
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }

}