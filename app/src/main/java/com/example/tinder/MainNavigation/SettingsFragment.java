package com.example.tinder.MainNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tinder.LoginActivity;
import com.example.tinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.tinder.MainNavigation.SlideFragmentStudent.getChildvalue;

public class SettingsFragment extends Fragment {

    //Creating a tag
    private String UID;

    private FirebaseAuth mAuth;
    private DatabaseReference Foto;
    private static final String TAG = "SettingsFragment";

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from settings_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        final View view = inflater.inflate(R.layout.settings_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();

        Button mbutton = (Button) view.findViewById(R.id.logoutuser);

        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("Debug Settings hoi", UID);

       Foto = FirebaseDatabase.getInstance().getReference().child("Users");
       Foto.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               if (dataSnapshot.exists()) {
                   UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                   if(dataSnapshot.getKey().contentEquals(UID)){
                   Log.d("Debug Settings", "Foto geladen");
                   ImageView image = (ImageView) view.findViewById(R.id.Profielfotoacount);
                   TextView Naambox = (TextView)  view.findViewById(R.id.Naamgebruiker);
                   String Naam = getChildvalue(dataSnapshot, "Name");
                   String FotoUrl1 = getChildvalue(dataSnapshot, "ProfileImageUrl");
                   Log.d("DebugSettings", Naam+FotoUrl1);
                   Naambox.setText(Naam);
                   //Glide.with(getContext()).load(FotoUrl1).into(image);
                   }

               } else {
                   Log.d("DebugSettings", "NOOOO Child");
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

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(),  LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                return;
            }
        });
        return view;
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
