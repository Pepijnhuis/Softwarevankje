package com.example.tinder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class NewMatch extends AppCompatActivity {

    String ownid, otherid, ownname, othername,ownpictureurl,otherpictureurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match);

        Log.d("Debug NewMatch", "Called");

        ownid = getIntent().getExtras().getString("ownid");
        otherid = getIntent().getExtras().getString("otherid");

        final TextView ownnametext = (TextView) findViewById(R.id.ownnametext);
        final TextView othernametext = (TextView) findViewById(R.id.othernametext);

        final ImageView ownpicture = (ImageView) findViewById(R.id.ownpicturebox);
        final ImageView otherpicture = (ImageView) findViewById(R.id.otherpicturebox);

        DatabaseReference owndata = FirebaseDatabase.getInstance().getReference().child("Users").child(ownid);
        DatabaseReference otherdata =FirebaseDatabase.getInstance().getReference().child("Users").child(otherid);

        owndata.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    ownname = getChildvalue(dataSnapshot,"Name");
                    ownpictureurl = getChildvalue(dataSnapshot,"ProfileImageUrl");
                    ownnametext.setText(ownname);
                    Glide.with(NewMatch.this).load(ownpictureurl).into(ownpicture);

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

        otherdata.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                othername = getChildvalue(dataSnapshot,"Name");
                otherpictureurl = getChildvalue(dataSnapshot,"ProfileImageUrl");
                othernametext.setText(othername);
                Glide.with(NewMatch.this).load(otherpictureurl).into(otherpicture);
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
