package com.example.tinder.CreateAccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tinder.Trash.MainNavigation;
import com.example.tinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountStudent extends AppCompatActivity implements CA1StudentFragment.FragmentCA1StudentListener, CA3StudentFragment.FragmentCA3StudentListener {

    private CA1StudentFragment ca1StudentFragment;
    private CA3StudentFragment ca3StudentFragment;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_student);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.containerCreateAccountStudent,new CA1StudentFragment());
        fragmentTransaction.commit();

        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onInputCA1StudentSent(String Email, String Password) {
        Log.d("Debug", "oninput sent called");

        Log.d("Debug", Email);
        Log.d("Debug", Password);
        mAuth= FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(CreateAccountStudent.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(CreateAccountStudent.this, "Signin Error", Toast.LENGTH_SHORT).show();
                    Log.d("Debug","gefaaaald!");
                } else {
                    Log.d("Debug", "Signup succesfullll!!!!!");

                }
            }
        });
    }

    @Override
    public void onInputCA3StudentSent(String NameStudent, String School, String Study, String Hobby1, String Hobby2, String Hobby3, String AboutMe) {
        Log.d("Debug", NameStudent);

    }
    //Main navigation button
    public void goToMainNavigation(View view) {
        Intent intent = new Intent (CreateAccountStudent.this, MainNavigation.class);
        startActivity(intent);
        return;
    }


}
