package com.example.tinder;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ChangeInformation extends AppCompatActivity {

    /*private EditText mNameField, mDayField, mMonthField, mYearField, mSchoolField,
            mStudyField, mHobby1Field, mHobby2Field, mHobby3Field, mAboutMeField;

    private RadioGroup mRadioGroupBscMsc, mRadioGroupMaleFemale;

    private RadioButton mMaleFemaleOption, mBscMscOption;

    private Button mSkip, mButtonNext;

    private FirebaseAuth mAuth;
    private DatabaseReference mStudentAccountDatabase;
    private String NameStudent, School, Study, Hobby1, Hobby2, Hobby3, AboutMe, userId, RadioGroupBscMsc, RadioGroupMaleFemale;
    private Integer Day, Month, Year, selectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_information);

        mNameField = (EditText) findViewById(R.id.NameStudent);
        mDayField = (EditText) findViewById(R.id.BirthdayDay);
        mMonthField = (EditText) findViewById(R.id.BirthdayMonth);
        mYearField = (EditText) findViewById(R.id.BirthdayYear);
        mSchoolField = (EditText) findViewById(R.id.SchoolStudent);
        mStudyField = (EditText) findViewById(R.id.StudyStudent);
        mHobby1Field = (EditText) findViewById(R.id.Hobby1);
        mHobby2Field = (EditText) findViewById(R.id.Hobby2);
        mHobby3Field = (EditText) findViewById(R.id.Hobby3);
        mAboutMeField = (EditText) findViewById(R.id.AboutMe);
        mRadioGroupMaleFemale = (RadioGroup) findViewById(R.id.RadioGroupMaleFemale);
        mRadioGroupBscMsc = (RadioGroup) findViewById(R.id.RadioGroupBscMsc);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mStudentAccountDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Student").child(userId);

        getUserInfo();

        mButtonNext = (Button) findViewById(R.id.ButtonNextCA3Student);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectId1 = mRadioGroupMaleFemale.getCheckedRadioButtonId();
                final RadioButton mRadioButtonMaleFemale = (RadioButton) findViewById(selectId1);

                int selectId2 = mRadioGroupBscMsc.getCheckedRadioButtonId();
                final RadioButton mRadioButtonBscMsc  = (RadioButton) findViewById(selectId2);

                NameStudent = mNameField.getText().toString();
                Day = Integer.parseInt(mDayField.getText().toString());
                Month = Integer.parseInt(mMonthField.getText().toString());
                Year = Integer.parseInt(mYearField.getText().toString());
                School = mSchoolField.getText().toString();
                Study = mStudyField.getText().toString();
                Hobby1 = mHobby1Field.getText().toString();
                Hobby2 = mHobby2Field.getText().toString();
                Hobby3 = mHobby3Field.getText().toString();
                AboutMe = mAboutMeField.getText().toString();
                RadioGroupMaleFemale = mRadioButtonMaleFemale.getText().toString();
                RadioGroupBscMsc = mRadioButtonBscMsc.getText().toString();

                //listener.onInputCA3StudentSent(NameStudent,School, Study, Hobby1, Hobby2, Hobby3, AboutMe);


                saveUserInformation();
            }
        });
        return;
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

        mStudentAccountDatabase.updateChildren(userInfo);

    }

    //Safe button
    public void goToChat(View view) {
        if (NameStudent != null && Day !=null && Month != null && Year != null
                && School != null && Study != null && Hobby1 != null && Hobby2 != null
                && Hobby3 != null && AboutMe != null){
            Intent intent = new Intent(ChangeInformation.this, MainNavigationStudent.class);
            startActivity(intent);
        }
        return;
    }*/

    private EditText mNameField, mDayField, mMonthField, mYearField, mSchoolField,
            mStudyField, mHobby1Field, mHobby2Field, mHobby3Field, mAboutMeField;

    private RadioGroup mRadioGroupBscMsc, mRadioGroupMaleFemale;

    private RadioButton mMaleFemaleOption, mBscMscOption;

    private Button mButtonBackChangeInformation, mButtonSafeInformation;

    private FirebaseAuth mAuth;
    private DatabaseReference mStudentAccountDatabase;
    private String NameStudent, School, Study, Hobby1, Hobby2, Hobby3, AboutMe, userId, RadioGroupBscMsc, RadioGroupMaleFemale;
    private String Day;
    private Integer Month;
    private Integer Year;
    private Integer selectId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_information);

        mNameField = (EditText) findViewById(R.id.NameStudent);
        //mDayField = (EditText) findViewById(R.id.BirthdayDay);
        //mMonthField = (EditText) findViewById(R.id.BirthdayMonth);
        //mYearField = (EditText) findViewById(R.id.BirthdayYear);
        mSchoolField = (EditText) findViewById(R.id.SchoolStudent);
        mStudyField = (EditText) findViewById(R.id.StudyStudent);
        mHobby1Field = (EditText) findViewById(R.id.Hobby1);
        mHobby2Field = (EditText) findViewById(R.id.Hobby2);
        mHobby3Field = (EditText) findViewById(R.id.Hobby3);
        mAboutMeField = (EditText) findViewById(R.id.AboutMe);
        mRadioGroupMaleFemale = (RadioGroup) findViewById(R.id.RadioGroupMaleFemale);
        mRadioGroupBscMsc = (RadioGroup) findViewById(R.id.RadioGroupBscMsc);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        mStudentAccountDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Student").child(userId);

        getUserInfo();
    }

    private void getUserInfo() {
        mStudentAccountDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if(map.get("Name")!=null){
                        NameStudent = map.get("Name").toString();
                        mNameField.setText(NameStudent);
                    }
                    if(map.get("School")!=null){
                        School = map.get("School").toString();
                        mSchoolField.setText(School);
                    }
                    if(map.get("Study")!=null){
                        Study = map.get("Study").toString();
                        mStudyField.setText(Study);
                    }
                    if(map.get("Hobby1")!=null){
                        Hobby1 = map.get("Hobby1").toString();
                        mHobby1Field.setText(Hobby1);
                    }
                    if(map.get("Hobby2")!=null){
                        Hobby2 = map.get("Hobby2").toString();
                        mHobby2Field.setText(Hobby2);
                    }
                    if(map.get("Hobby3")!=null){
                        Hobby3 = map.get("Hobby3").toString();
                        mHobby3Field.setText(Hobby3);
                    }
                    if(map.get("AboutMe")!=null){
                        AboutMe = map.get("AboutMe").toString();
                        mAboutMeField.setText(AboutMe);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void saveUserInformation() {
        NameStudent = mNameField.getText().toString();
        Day = mDayField.getText().toString();
        //Day = Integer.parseInt(mDayField.getText().toString());
        //Month = Integer.parseInt(mMonthField.getText().toString());
        //Year = Integer.parseInt(mYearField.getText().toString());
        School = mSchoolField.getText().toString();
        Study = mStudyField.getText().toString();
        Hobby1 = mHobby1Field.getText().toString();
        Hobby2 = mHobby2Field.getText().toString();
        Hobby3 = mHobby3Field.getText().toString();
        AboutMe = mAboutMeField.getText().toString();
        //RadioGroupMaleFemale = mRadioButtonMaleFemale.getText().toString();
        //RadioGroupBscMsc = mRadioButtonBscMsc.getText().toString();

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

        mStudentAccountDatabase.updateChildren(userInfo);
    }

    //back button
    public void goToMainNavigationStudent(View view) {
        Intent intent = new Intent(ChangeInformation.this, MainNavigationStudent.class);
        startActivity(intent);
        return;
    }

    //back button
    public void goToMainNavigationStudentWithSave(View view) {
        /*if (NameStudent != null && Day != null && Month != null && Year != null
                && School != null && Study != null && Hobby1 != null && Hobby2 != null
                && Hobby3 != null && AboutMe != null) {
            saveUserInformation();
            Intent intent = new Intent(ChangeInformation.this, MainNavigationStudent.class);
            startActivity(intent);
            return;
        }*/
        saveUserInformation();
        Intent intent = new Intent(ChangeInformation.this, MainNavigationStudent.class);
        startActivity(intent);
        return;

    }
}