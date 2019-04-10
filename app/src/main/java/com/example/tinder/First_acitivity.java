package com.example.tinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.tinder.CreateAccount.StudentHouseActivity;
import com.example.tinder.MainNavigation.MainNavigationHouse;
import com.example.tinder.MainNavigation.MainNavigationStudent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




public class First_acitivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private DatabaseReference usersDb;
    private FirebaseAuth mAuth;

    private String userRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_acitivity);

        usersDb = FirebaseDatabase.getInstance().getReference().child("Users");

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("Debug", "Hoi");
                    Log.d("Debug", "Checkusersex called");
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
                                    Intent intent = new Intent(First_acitivity.this, MainNavigationStudent.class);
                                    startActivity(intent);
                                    break;
                                case "House":
                                    Intent intent2 = new Intent(First_acitivity.this, MainNavigationHouse.class);
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Log.d("Debug", "Gebruiker ingelogd");
        } else {
            Log.d("Debug", "Geen gebruiker ingelogd");
            Intent intent = new Intent(First_acitivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
