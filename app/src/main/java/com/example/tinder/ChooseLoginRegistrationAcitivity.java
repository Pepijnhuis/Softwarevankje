package com.example.tinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseLoginRegistrationAcitivity extends AppCompatActivity {

    private Button mlogin, mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_registration_acitivity);

        mlogin = (Button) findViewById(R.id.login);
        mRegister = (Button) findViewById(R.id.register);

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLoginRegistrationAcitivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLoginRegistrationAcitivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

}
