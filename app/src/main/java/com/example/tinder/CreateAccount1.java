package com.example.tinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateAccount1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account1);
    }

    //back button
    public void goToLogIn(View view) {
        Intent intent = new Intent (CreateAccount1.this, LoginActivity.class);
        startActivity(intent);
        return;
    }

    //next button
    public void goToCreateAccount2(View view) {
        Intent intent = new Intent (CreateAccount1.this, CreateAccount2.class);
        startActivity(intent);
        return;
    }
}
