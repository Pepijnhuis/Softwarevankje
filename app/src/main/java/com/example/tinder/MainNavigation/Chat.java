package com.example.tinder.MainNavigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tinder.R;
import com.example.tinder.Trash.MainNavigation;

public class Chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    //Main navigation button
    public void goToMainNavigation(View view) {
        Intent intent = new Intent(Chat.this, MainNavigation.class);
        startActivity(intent);
        return;
    }
}
