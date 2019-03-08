package com.example.tinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateAccount3Student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account3_student);
    }

    public void goToCreateAccount2(View view) {
        Intent intent = new Intent (CreateAccount3Student.this, CreateAccount2.class);
        startActivity(intent);
        return;
    }

    public void goToCreateAccount4Student(View view) {
        Intent intent = new Intent (CreateAccount3Student.this, CreateAccount4Student.class);
        startActivity(intent);
        return;
    }
}
