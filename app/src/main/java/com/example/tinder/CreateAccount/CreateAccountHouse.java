package com.example.tinder.CreateAccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tinder.MainNavigation;
import com.example.tinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountHouse extends AppCompatActivity implements CA1HouseFragment.FragmentCA1HouseListener {
    private CA1HouseFragment ca1HouseFragment;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_house);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.containerCreateAccountHouse,new CA1HouseFragment());
        fragmentTransaction.commit();

        mAuth = FirebaseAuth.getInstance();

        ca1HouseFragment = new CA1HouseFragment();
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
        Intent intent = new Intent(CreateAccountHouse.this, MainNavigation.class);
        startActivity(intent);
        return;
    }
}
