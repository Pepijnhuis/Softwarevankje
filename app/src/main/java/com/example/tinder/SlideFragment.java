package com.example.tinder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;


public class SlideFragment extends Fragment {
    private ArrayList<String> al;
    private Cards cards_data[];
    private arrayAdapter arrayAdapter;
    private int i;
    private String Key, Naam, School, Hobby1, Hobby2, Hobby3, Aboutme;

    ListView listView;
    List<Cards> rowItems;

    private DatabaseReference usersDB;

    private String currentUId;
    private String usersex;


    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();


        usersDB = FirebaseDatabase.getInstance().getReference().child("Users");
        currentUId = mAuth.getCurrentUser().getUid();


        checkUserSex();

        mAuth = FirebaseAuth.getInstance();

        rowItems = new ArrayList<Cards>();
        //Make the array of cards
        arrayAdapter = new arrayAdapter(getContext(), R.layout.item, rowItems );
        //al = new ArrayList<>();
        //al.add("php");

        //Something to do with the Swipecards plugin from github:  https://github.com/Diolor/Swipecards
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) view.findViewById(R.id.frameSlide);


        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("Debug", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject

                Cards obj = (Cards) dataObject;
                String userId = obj.getUserId();
                usersDB.child(oppositeUserSex).child(userId).child("connections").child("nope").child(currentUId).setValue(true);

                Toast.makeText(getActivity(), "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Cards obj =(Cards) dataObject;
                String userId = obj.getUserId();
                usersDB.child(oppositeUserSex).child(userId).child("connections").child("yeps").child(currentUId).setValue(true);
                isConnectionMatch(userId);
                Toast.makeText(getActivity(), "Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                Log.d("Debug", "Leeg");
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

    private void isConnectionMatch(String userId) {
        DatabaseReference currentUserConnectionsDb = usersDB.child(usersex).child(currentUId).child("connections").child("yeps").child(userId);
        currentUserConnectionsDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(getActivity(), "new Connection", Toast.LENGTH_LONG).show();
                    usersDB.child(oppositeUserSex).child(dataSnapshot.getKey()).child("connections").child("matches").child(currentUId).setValue(true);
                    usersDB.child(usersex).child(currentUId).child("connections").child("matches").child(dataSnapshot.getKey()).setValue(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private String oppositeUserSex;
    public void checkUserSex(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("Debug", "Checkusersex called");
        DatabaseReference Studentdb = FirebaseDatabase.getInstance().getReference().child("Users").child("Student");
        Studentdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String UID = user.getUid();
                Log.d("Debug", UID);
                if (dataSnapshot.getKey().equals(user.getUid())){
                    oppositeUserSex = "Huis";
                    usersex = "Student";
                    Log.d("Debug",oppositeUserSex);
                    getOppositeSexUsers();
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        DatabaseReference HouseDb = FirebaseDatabase.getInstance().getReference().child("Users").child("Huis");
        HouseDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey().equals(user.getUid())){
                    usersex = "Huis";
                    oppositeUserSex = "Student";
                    Log.d("Debug",oppositeUserSex);
                    getOppositeSexUsers();
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void getOppositeSexUsers(){
        DatabaseReference oppositeSexDb = FirebaseDatabase.getInstance().getReference().child("Users").child(oppositeUserSex);
        oppositeSexDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists() && !dataSnapshot.child("connections").child("nope").hasChild(currentUId) && !dataSnapshot.child("connections").child("yeps").hasChild(currentUId)){
                    Key = dataSnapshot.getKey();
                    Naam = dataSnapshot.child("Name").getValue().toString();
                    School = dataSnapshot.child("School").getValue().toString();
                    Hobby1 = dataSnapshot.child("Hobby1").getValue().toString();
                    Hobby2 = dataSnapshot.child("Hobby2").getValue().toString();
                    Hobby3 = dataSnapshot.child("Hobby3").getValue().toString();
                    Aboutme = dataSnapshot.child("AboutMe").getValue().toString();
                    Cards Item = new Cards(Key, Naam, School, Hobby1, Hobby2, Hobby3, Aboutme);
                    rowItems.add(Item);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}