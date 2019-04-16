package com.example.tinder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Changepicture extends AppCompatActivity {

    private String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepicture);


        DatabaseReference Foto = FirebaseDatabase.getInstance().getReference().child("Users");
        Foto.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
                    UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    if (dataSnapshot.getKey().contentEquals(UID)) {
                        Log.d("Debug Settings", "Foto geladen");
                        ImageView image = (ImageView) findViewById(R.id.Profielfotoacount);
                        String FotoUrl1 = getChildvalue(dataSnapshot, "ProfileImageUrl");
                        if (FotoUrl1.contentEquals("")) {
                            Log.d("Debug Settings", "Settings glide bug");
                        } else {
                            Glide.with(image.getContext()).load(FotoUrl1).into(image);
                        }
                    }
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static String getChildvalue (DataSnapshot dataSnapshot, String key){
        try{
            String Res = dataSnapshot.child(key).getValue().toString();
            return Res;
        }
        catch (NullPointerException e){
            return "";
        }
    }
}

