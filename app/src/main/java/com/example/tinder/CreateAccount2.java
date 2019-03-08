package com.example.tinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateAccount2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account2);
    }

    //back button
    public void goToCreateAccount1(View view) {
        Intent intent = new Intent (CreateAccount2.this, CreateAccount1.class);
        startActivity(intent);
        return;
    }

    //Looking for house
    public void goToCreateAccount2Student(View view) {
        Intent intent = new Intent (CreateAccount2.this, CreateAccount3Student.class);
        startActivity(intent);
        return;
    }

    //Looking for student
    public void goToCreateAccount3House(View view) {
        Intent intent = new Intent (CreateAccount2.this, CreateAccount3Student.class);
        startActivity(intent);
        return;
    }
}
