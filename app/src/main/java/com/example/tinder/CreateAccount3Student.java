package com.example.tinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount3Student extends AppCompatActivity {

    private EditText mNameField, mAdressField, mDayField, mMonthField, mYearField, mSchoolField,
            mStudyField, mHobby1Field, mHobby2Field, mHobby3Field, mAboutMeField;

    private RadioGroup mBscMscRadioGroup;

    private Button mBack, mNext, mConfirm;

    private FirebaseAuth mAuth;
    private DatabaseReference mStudentAccountDatabase;
    private String Name, Adress, School, Study, Hobby1, Hobby2, Hobby3, AboutMe;
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

        mBscMscRadioGroup = (RadioGroup) findViewById(R.id.RadioGroupBscMsc);

       // mBack = (Button) findViewById(R.id.NameStudent);
        mNext = (Button) findViewById(R.id.nextCreateAccount3Student);

       mNext.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });


    }

    //back button
    public void goToCreateAccount2(View view) {
        Intent intent = new Intent (CreateAccount3Student.this, CreateAccount2.class);
        startActivity(intent);
        return;
    }

    //next button
    public void goToCreateAccount4Student(View view) {
        Intent intent = new Intent (CreateAccount3Student.this, CreateAccount4Student.class);
        startActivity(intent);
        return;
    }
}
