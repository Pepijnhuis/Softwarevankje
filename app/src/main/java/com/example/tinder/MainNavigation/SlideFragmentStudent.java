package com.example.tinder.MainNavigation;

import android.icu.util.LocaleData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tinder.Cards.CardsStudent;
import com.example.tinder.Cards.CardsHouse;
import com.example.tinder.Cards.arrayAdapterHouse;
import com.example.tinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class SlideFragmentStudent extends Fragment {
    private ArrayList<String> al;
    private CardsStudent cards_data[];
    private arrayAdapterHouse arrayAdapter;
    private String Key, Name, Address, Size, Rent, NumberHouseMates, AboutMe, Picture, Year,Maand,Dag, Maxhousemates, MaxRent;
    private Integer maxrentint, rentint;


    ListView listView;
    List<CardsHouse> rowItems;

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

        getPrefrences();
        getOppositeSexUsers();

        mAuth = FirebaseAuth.getInstance();

        rowItems = new ArrayList<CardsHouse>();
        //Make the array of cards
        arrayAdapter = new arrayAdapterHouse(getContext(), R.layout.item_student, rowItems );
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

                CardsHouse obj = (CardsHouse) dataObject;
                String userId = obj.getUserId();
                usersDB.child(userId).child("connections").child("nope").child(currentUId).setValue(true);

                Toast.makeText(getActivity(), "No", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                CardsHouse obj =(CardsHouse) dataObject;
                String userId = obj.getUserId();
                Log.d("Debug", userId);
                usersDB.child(userId).child("connections").child("yeps").child(currentUId).setValue(true);
                isConnectionMatch(userId);
                Toast.makeText(getActivity(), "Yes", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });

        return view;

    }

    private void isConnectionMatch(String userId) {
        DatabaseReference currentUserConnectionsDb = usersDB.child(currentUId).child("connections").child("yeps").child(userId);
        currentUserConnectionsDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Toast.makeText(getActivity(), "new Match", Toast.LENGTH_LONG).show();

                    String key = FirebaseDatabase.getInstance().getReference().child("Chat").push().getKey();

                    //create chat id
                    usersDB.child(dataSnapshot.getKey()).child("connections").child("matches").child(currentUId).child("ChatId").setValue(key);
                    usersDB.child(currentUId).child("connections").child("matches").child(dataSnapshot.getKey()).child("ChatId").setValue(key);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private String oppositeUserSex = "House";

    public void getOppositeSexUsers(){
        DatabaseReference oppositeSexDb = FirebaseDatabase.getInstance().getReference().child("Users");
        oppositeSexDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists() && !dataSnapshot.child("connections").child("nope").hasChild(currentUId) && !dataSnapshot.child("connections").child("yeps").hasChild(currentUId) && dataSnapshot.child("Register").getValue().toString().equals(oppositeUserSex)){
                        Rent = getChildvalue(dataSnapshot,"Rent");
                        Log.d("Debug FragmentStudent", Rent);
                        if (Rent.contentEquals("")){
                            Rent = "0";
                        }
                        rentint = Integer.parseInt(Rent);
                        if(rentint > maxrentint){
                            Log.d("Debug Fragment Student", "Rent is te hoog");
                        }
                        else {
                            Key = dataSnapshot.getKey();
                            Name = getChildvalue(dataSnapshot, "Address");
                            Address = getChildvalue(dataSnapshot, "Name");
                            NumberHouseMates = getChildvalue(dataSnapshot, "NumeberHousemates");
                            Rent = getChildvalue(dataSnapshot, "Rent");
                            Size = getChildvalue(dataSnapshot, "Size");
                            AboutMe = getChildvalue(dataSnapshot, "AboutUs");
                            Picture = getChildvalue(dataSnapshot, "ProfileImageUrl");
                            Log.d("Debug", Key + Name + Address + AboutMe + Size + Rent + NumberHouseMates + Picture);
                            CardsHouse Item = new CardsHouse(Key, Name, Address, Rent, Size, NumberHouseMates, AboutMe, Picture);

                            rowItems.add(Item);
                            arrayAdapter.notifyDataSetChanged();
                        }
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

    public void getPrefrences(){
        DatabaseReference userdb = FirebaseDatabase.getInstance().getReference().child("Users");
        userdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                currentUId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if(dataSnapshot.getKey().contentEquals(currentUId)){
                    MaxRent = getChildvalue(dataSnapshot, "MaxRent");
                    maxrentint = Integer.parseInt(MaxRent);
                    Maxhousemates = getChildvalue(dataSnapshot, "MsxHouseMates");
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
