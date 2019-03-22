package com.example.tinder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public static class NewAccountData extends CreateAccountStudent{
    public static String Email, Password, NameStudent, AdressStudent, School, Study, Hobby1, Hobby2, Hobby3, AboutMe, userId;
    private Integer Day, Month, Year;

    private FirebaseAuth mAuth;
    public void MakeAccount(){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            String hoi = "hoi";
                        }
                        else{
                            String userID = mAuth.getCurrentUser().getUid();
                        }
                    }
                });
            }
    }

