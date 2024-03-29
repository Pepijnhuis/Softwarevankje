package com.example.tinder.CreateAccount;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class CA3StudentFragment extends Fragment {
    private CA3StudentFragment.FragmentCA3StudentListener listener;
    //Creating a tag
    private static final String TAG = "CA3StudentFragment";

    public interface FragmentCA3StudentListener {
        void onInputCA3StudentSent(String NameStudent, String School, String Study, String Hobby1, String Hobby2, String Hobby3, String AboutMe);
    }



    private EditText mNameField, mDayField, mMonthField, mYearField, mSchoolField,
            mStudyField, mHobby1Field, mHobby2Field, mHobby3Field, mAboutMeField;

    private RadioGroup mRadioGroupBscMsc, mRadioGroupMaleFemale;

    private RadioButton mMaleFemaleOption, mBscMscOption;

    private Button mButtonNext;

    private FirebaseAuth mAuth;
    private DatabaseReference mStudentAccountDatabase;
    private String NameStudent, School, Study, Hobby1, Hobby2, Hobby3, AboutMe, userId, RadioGroupBscMsc, RadioGroupMaleFemale, Day, Month, Year;


    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        final View view = inflater.inflate(R.layout.ca3_student_fragment, container, false);


        mNameField = (EditText) view.findViewById(R.id.NameStudent);
        mDayField = (EditText) view.findViewById(R.id.BirthdayDay);
        mMonthField = (EditText) view.findViewById(R.id.BirthdayMonth);
        mYearField = (EditText) view.findViewById(R.id.BirthdayYear);
        mSchoolField = (EditText) view.findViewById(R.id.SchoolStudent);
        mStudyField = (EditText) view.findViewById(R.id.StudyStudent);
        mHobby1Field = (EditText) view.findViewById(R.id.Hobby1);
        mHobby2Field = (EditText) view.findViewById(R.id.Hobby2);
        mHobby3Field = (EditText) view.findViewById(R.id.Hobby3);
        mAboutMeField = (EditText) view.findViewById(R.id.AboutMe);
        mRadioGroupMaleFemale = (RadioGroup) view.findViewById(R.id.RadioGroupMaleFemale);
        mRadioGroupBscMsc = (RadioGroup) view.findViewById(R.id.RadioGroupBscMsc);

        mButtonNext = (Button) view.findViewById(R.id.ButtonNextCA3Student);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectId1 = mRadioGroupMaleFemale.getCheckedRadioButtonId();
                final RadioButton mRadioButtonMaleFemale = (RadioButton) view.findViewById(selectId1);

                int selectId2 = mRadioGroupBscMsc.getCheckedRadioButtonId();
                final RadioButton mRadioButtonBscMsc  = (RadioButton) view.findViewById(selectId2);

                NameStudent = mNameField.getText().toString();
                Day = mDayField.getText().toString();
                Month = mMonthField.getText().toString();
                Year = mYearField.getText().toString();
                School = mSchoolField.getText().toString();
                Study = mStudyField.getText().toString();
                Hobby1 = mHobby1Field.getText().toString();
                Hobby2 = mHobby2Field.getText().toString();
                Hobby3 = mHobby3Field.getText().toString();
                AboutMe = mAboutMeField.getText().toString();
                RadioGroupMaleFemale = mRadioButtonMaleFemale.getText().toString();
                RadioGroupBscMsc = mRadioButtonBscMsc.getText().toString();

                listener.onInputCA3StudentSent(NameStudent,School, Study, Hobby1, Hobby2, Hobby3, AboutMe);

                mAuth = FirebaseAuth.getInstance();
                userId = mAuth.getCurrentUser().getUid();
                mStudentAccountDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

                saveUserInformation();

                //next fragment
                if (NameStudent != null && Day !=null && Month != null && Year != null
                && School != null && Study != null && Hobby1 != null && Hobby2 != null
                && Hobby3 != null && AboutMe != null){
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.containerCreateAccountStudent,new CA4StudentFragment());
                    fr.commit();
                }
        }
        });
    return view;
    }

        private void saveUserInformation() {
            Map userInfo = new HashMap();
            userInfo.put("Name", NameStudent);
            userInfo.put("Day", Day);
            userInfo.put("Month", Month);
            userInfo.put("Year", Year);
            userInfo.put("School", School);
            userInfo.put("Study", Study);
            userInfo.put("Hobby1", Hobby1);
            userInfo.put("Hobby2", Hobby2);
            userInfo.put("Hobby3", Hobby3);
            userInfo.put("AboutMe", AboutMe);
            userInfo.put("MaleFemale", RadioGroupMaleFemale);
            userInfo.put("BscMSc", RadioGroupBscMsc);
            userInfo.put("Register", "Student");

            mStudentAccountDatabase.updateChildren(userInfo);

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