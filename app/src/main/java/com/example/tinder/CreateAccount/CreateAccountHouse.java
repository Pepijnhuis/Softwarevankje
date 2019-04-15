package com.example.tinder.CreateAccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tinder.MainNavigation.MainNavigationHouse;
import com.example.tinder.Trash.MainNavigation;
import com.example.tinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountHouse extends AppCompatActivity implements CA1HouseFragment.FragmentCA1HouseListener {
    private CA1HouseFragment ca1HouseFragment;

    private FirebaseAuth mAuth;

    private Button mButtonNext;
    private EditText mMinAge;
    private EditText mMaxAge;

    private String MinAge, MaxAge, userId;
    private DatabaseReference mStudentAccountDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_house);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.containerCreateAccountHouse,new CA1HouseFragment());
        fragmentTransaction.commit();

        mAuth = FirebaseAuth.getInstance();

        ca1HouseFragment = new CA1HouseFragment();

        //next button
        mButtonNext = (Button) findViewById(R.id.ButtonNextCA5House);

        mMinAge = (EditText) findViewById(R.id.MaxAge);
        mMaxAge = (EditText) findViewById(R.id.MinAge);

        /*mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MinAge = mMinAge.getText().toString();
                MaxAge = mMaxAge.getText().toString();

                mAuth = FirebaseAuth.getInstance();
                userId = mAuth.getCurrentUser().getUid();
                mStudentAccountDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

                saveUserInformation();

                //next button
                if (MinAge !=null && MaxAge != null) {
                    Intent intent = new Intent(CreateAccountHouse.this, MainNavigationHouse.class);
                    startActivity(intent);
                }
            }
        });*/
    }

    @Override
    public void onInputCA1HouseSent(String Email, String Password) {
        Log.d("Debug", "oninput sent called");
        Log.d("Debug",Email);
        Log.d("Debug",Password);
        mAuth= FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(CreateAccountHouse.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(CreateAccountHouse.this, "Signin Error", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Debug", "Signup succesfullll!!!!!");

                }
            }
        });
    }
    //Main navigation button
    public void goToMainNavigation(View view) {
            MinAge = mMinAge.getText().toString();
            MaxAge = mMaxAge.getText().toString();

            mAuth = FirebaseAuth.getInstance();
            userId = mAuth.getCurrentUser().getUid();
            mStudentAccountDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

            saveUserInformation();

            //next button
            if (MinAge !=null && MaxAge != null) {
                Intent intent = new Intent(CreateAccountHouse.this, MainNavigationHouse.class);
                startActivity(intent);
            }
        return;
    }


    private void saveUserInformation() {
        Map userInfo = new HashMap();
        userInfo.put("MinAge", MinAge);
        userInfo.put("MaxAge", MaxAge);

        mStudentAccountDatabase.updateChildren(userInfo);

    }
}
