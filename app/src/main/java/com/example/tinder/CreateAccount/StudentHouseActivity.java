package com.example.tinder.CreateAccount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tinder.R;

public class StudentHouseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_house);
    }

    //Looking for house
    public void goLookingForHouse(View view) {
        //Intent intent = new Intent (StudentHouseActivity.this, CreateAccountStudent.class);

        //Temporary
        Intent intent = new Intent (StudentHouseActivity.this, CreateAccountStudent.class);

        startActivity(intent);
        return;
    }

    //Looking for student
    public void goLookingForStudent(View view) {
        Intent intent = new Intent (StudentHouseActivity.this, CreateAccountHouse.class);
        startActivity(intent);
        return;
    }
}
