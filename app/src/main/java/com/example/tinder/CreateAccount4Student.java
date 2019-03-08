package com.example.tinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateAccount4Student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account4_student);
    }

    //back button
    public void goToCreateAccount3Student(View view) {
        Intent intent = new Intent (CreateAccount4Student.this, CreateAccount3Student.class);
        startActivity(intent);
        return;
    }

    //next button
    public void goToCreateAccount5Student(View view) {
        Intent intent = new Intent (CreateAccount4Student.this, CreateAccount5Student.class);
        startActivity(intent);
        return;
    }
}
