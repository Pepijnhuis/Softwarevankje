package com.example.tinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateAccount4House extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account4_house);
    }

    //back button
    public void goToCreateAccount3House(View view) {
        Intent intent = new Intent (CreateAccount4House.this, CreateAccount3House.class);
        startActivity(intent);
        return;
    }

    //next button
    public void goToCreateAccount5House(View view) {
        Intent intent = new Intent (CreateAccount4House.this, CreateAccount5House.class);
        startActivity(intent);
        return;
    }
}
