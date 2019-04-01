package com.example.tinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tinder.CreateAccount.StudentHouseActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button mLogin;

    private EditText mEmail,mPassword;
    private Button mRegisterbutton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user !=null){
                    Intent intent = new Intent(LoginActivity.this, MainNavigation.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        mLogin = (Button) findViewById(R.id.loginaccount);

        mEmail = (EditText) findViewById(R.id.emailbox);
        mPassword = (EditText) findViewById(R.id.passwordbox);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();

                if (email.matches("") ) {
                    // Show Error on edittext
                    mEmail.setError("Invalid email");
                    Log.d("Debug", "no email");

                    //invalid email and password
                    if (password.matches("")) {
                        // Show Error on edittext
                        mPassword.setError("Invalid password");
                        Log.d("Debug", "no password");}
                    return;
                }

                //invalid password
                if (password.matches("")) {
                    // Show Error on edittext
                    mPassword.setError("Invalid password");
                    Log.d("Debug", "no password");
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //when login is successful
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Signin Error", Toast.LENGTH_SHORT).show();
                        }
                        //when login is not successful
                        else{
                            mEmail.setError("Invalid email");
                            Log.d("Debug", "no email");
                            mPassword.setError("Invalid password");
                            Log.d("Debug", "no password");
                        }

                        //    String userID = mAuth.getCurrentUser().getUid();
                        //    DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("Student");
                        //    currentUserDb.setValue(name);

                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }

    //Create account button
    public void goToStudentHouse(View view) {
        Intent intent = new Intent (LoginActivity.this, StudentHouseActivity.class);
        startActivity(intent);
        return;
    }

    //Main navigation test button
    public void goToMainNavigation(View view) {
        Intent intent = new Intent (LoginActivity.this, MainNavigation.class);
        startActivity(intent);
        return;
    }
}



