package com.example.tinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateAccount3House extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account3_house);
    }

    //back button
    public void goToCreateAccount2(View view) {
        Intent intent = new Intent (CreateAccount3House.this, CreateAccount2.class);
        startActivity(intent);
        return;
    }

    //next button
    public void goToCreateAccount4House(View view) {
        Intent intent = new Intent (CreateAccount3House.this, CreateAccount4House.class);
        startActivity(intent);
        return;
    }
}
