package com.example.tinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tinder.CreateAccount.StudentHouseActivity;
import com.example.tinder.MainNavigation.MainNavigationHouse;
import com.example.tinder.MainNavigation.MainNavigationStudent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private Button mLogin;

    private EditText mEmail, mPassword;
    private Button mRegisterbutton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    private String CurrentUserID, userRegistration, oppositeUserRegistration;

    private DatabaseReference usersDb;

    private DatabaseReference Studentdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usersDb = FirebaseDatabase.getInstance().getReference().child("Users");

        mAuth = FirebaseAuth.getInstance();


        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("Debug", user.getUid());
                    FirebaseDatabase.getInstance();
                    DatabaseReference UserDB = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
                    UserDB.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            userRegistration = dataSnapshot.child("Register").getValue().toString();
                            Log.d("Debug", userRegistration);
                            switch (userRegistration) {
                                case "Student":
                                    Intent intent = new Intent(LoginActivity.this, MainNavigationStudent.class);
                                    startActivity(intent);
                                    break;
                                case "House":
                                    Intent intent2 = new Intent(LoginActivity.this, MainNavigationHouse.class);
                                    startActivity(intent2);
                                    break;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }
        };
        /*
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            //when logged in successful
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                Log.d("Debug","onAuthStateChanged" );
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                CurrentUserID = FirebaseAuth.getInstance().getUid();
                //Log.d("Debug, CurrentUserID",CurrentUserID );

                if (user !=null) {
                    DatabaseReference userDb = usersDb.child(user.getUid());
                    userDb.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                if (dataSnapshot.child("Register").getValue() != null) {
                                    userRegistration = dataSnapshot.child("Registration").getValue().toString();
                                    switch (userRegistration) {
                                        case "House":
                                            oppositeUserRegistration = "Student";
                                            Log.d("Debug register", userRegistration);
                                            Intent intent1 = new Intent(LoginActivity.this, MainNavigationHouse.class);
                                            startActivity(intent1);
                                            break;
                                        case "Student":
                                            oppositeUserRegistration = "House";
                                            Log.d("Debug register", userRegistration);
                                            Intent intent2 = new Intent(LoginActivity.this, MainNavigationStudent.class);
                                            startActivity(intent2);
                                            break;
                                    }
                                }
                            }
                            //getOppositeUserRegistration();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });

                }
            }
        };
        */
        mLogin = (Button) findViewById(R.id.loginaccount);
        mEmail = (EditText) findViewById(R.id.emailbox);
        mPassword = (EditText) findViewById(R.id.passwordbox);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                Log.d("Debug, email edittext", email);
                Log.d("Debug, password edittext", password);


                if (email.matches("")) {
                    // Show Error on edittext
                    mEmail.setError("Invalid email");
                    Log.d("Debug", "empty email");

                    //invalid email and password
                    if (password.matches("")) {
                        // Show Error on edittext
                        mPassword.setError("Invalid password");
                        Log.d("Debug", "empty password");
                    }
                    return;
                }


                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //when login is not successful
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Signin Error", Toast.LENGTH_SHORT).show();
                            mEmail.setError("Invalid email");
                            Log.d("Debug", "invalid email");
                            mPassword.setError("Invalid password");
                            Log.d("Debug", "invalid password");
                        } else {
                            Log.d("Debug", "successful login");
                        }
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


    public void goToStudentHouse(View v){
    Intent intent = new Intent(LoginActivity.this, StudentHouseActivity.class);
    startActivity(intent);
    }

}
    //Create account button

