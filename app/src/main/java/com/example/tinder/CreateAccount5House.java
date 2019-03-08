package com.example.tinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateAccount5House extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account5_house);
    }

    //back button
    public void goToCreateAccount4House(View view) {
        Intent intent = new Intent (CreateAccount5House.this, CreateAccount4House.class);
        startActivity(intent);
        return;
    }

    //next button
    public void goToSlideActivity(View view) {
        Intent intent = new Intent (CreateAccount5House.this, Slideactivity.class);
        startActivity(intent);
        return;
    }
}