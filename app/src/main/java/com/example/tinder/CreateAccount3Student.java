package com.example.tinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class CreateAccount3Student extends AppCompatActivity {

    private EditText mNameField, mAdressField, mDayField, mMonthField, mYearField, mSchoolField,
            mStudyField, mHobby1Field, mHobby2Field, mHobby3Field, mAboutMeField;

    private RadioGroup mRadioGroupBscMsc, mRadioGroupMaleFemale;

    private Button mBack, mNext, mConfirm;

    private FirebaseAuth mAuth;
    private DatabaseReference mStudentAccountDatabase;
    private String NameStudent, AdressStudent, School, Study, Hobby1, Hobby2, Hobby3, AboutMe, userId;
    private Integer Day, Month, Year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account3_student);

        mNameField = (EditText) findViewById(R.id.NameStudent);
        mAdressField = (EditText) findViewById(R.id.AddressHouse);
        mDayField = (EditText) findViewById(R.id.BirthdayDay);
        mMonthField = (EditText) findViewById(R.id.BirthdayMonth);
        mYearField = (EditText) findViewById(R.id.BirthdayYear);
        mSchoolField = (EditText) findViewById(R.id.SchoolStudent);
        mStudyField = (EditText) findViewById(R.id.StudyStudent);
        mHobby1Field = (EditText) findViewById(R.id.Hobby1);
        mHobby2Field = (EditText) findViewById(R.id.Hobby2);
        mHobby3Field = (EditText) findViewById(R.id.Hobby3);
        mAboutMeField = (EditText) findViewById(R.id.AboutMe);

        mRadioGroupBscMsc = (RadioGroup) findViewById(R.id.RadioGroupBscMsc);
        mRadioGroupMaleFemale = (RadioGroup) findViewById(R.id.RadioGroupMaleFemale);

        // mBack = (Button) findViewById(R.id.NameStudent);
        mNext = (Button) findViewById(R.id.nextCreateAccount3Student);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mStudentAccountDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Students").child("UserId");

        getUserInfo();

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveUserInformation();

            }
        });


    }

    private void getUserInfo() {
        mStudentAccountDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("Name") != null) {
                        NameStudent = map.get("Name").toString();
                        mNameField.setText(NameStudent);
                    }

                    if (map.get("Name") != null) {
                        AdressStudent = map.get("Adress").toString();
                        mAdressField.setText(AdressStudent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveUserInformation() {
        NameStudent = mNameField.getText().toString();
        AdressStudent = mAdressField.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("Name", NameStudent);
        userInfo.put("Adress", AdressStudent);
        mStudentAccountDatabase.updateChildren(userInfo);

    }

}