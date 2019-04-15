package com.example.tinder.MainNavigation;

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
import com.example.tinder.Cards.arrayAdapterStudent;
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

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public class SlideFragmentHouse extends Fragment {
    private ArrayList<String> al;
    private CardsStudent cards_data[];
    private arrayAdapterStudent arrayAdapter;
    private int i;
    private String Key, Naam, School, Hobby1, Hobby2, Hobby3, Aboutme, Picture,Year,Maand,Dag,Leeftijds,BscMsc;

    ListView listView;
    List<CardsStudent> rowItems;

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


        getOppositeSexUsers();

        mAuth = FirebaseAuth.getInstance();

        rowItems = new ArrayList<CardsStudent>();
        //Make the array of cards
        arrayAdapter = new arrayAdapterStudent(getContext(), R.layout.item_house, rowItems );
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

                CardsStudent obj = (CardsStudent) dataObject;
                String userId = obj.getUserId();
                usersDB.child(userId).child("connections").child("nope").child(currentUId).setValue(true);

                Toast.makeText(getActivity(), "No", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                CardsStudent obj =(CardsStudent) dataObject;
                String userId = obj.getUserId();
                usersDB.child(userId).child("connections").child("yeps").child(currentUId).setValue(true);
                isConnectionMatch(userId);
                Toast.makeText(getActivity(), "Yes", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
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

    private String oppositeUserSex = "Student";
    public void getOppositeSexUsers(){
        DatabaseReference oppositeSexDb = FirebaseDatabase.getInstance().getReference().child("Users");
        oppositeSexDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists() && !dataSnapshot.child("connections").child("nope").hasChild(currentUId) && !dataSnapshot.child("connections").child("yeps").hasChild(currentUId) && dataSnapshot.child("Register").getValue().toString().equals(oppositeUserSex)){
                    Key = dataSnapshot.getKey();
                    Naam = getChildvalue(dataSnapshot,"Name");
                    School = getChildvalue(dataSnapshot,"School");
                    Hobby1 = getChildvalue(dataSnapshot,"Hobby1");
                    Hobby2 = getChildvalue(dataSnapshot,"Hobby2");
                    Hobby3 = getChildvalue(dataSnapshot,"Hobby3");
                    Aboutme = getChildvalue(dataSnapshot,"AboutMe");
                    Picture = getChildvalue(dataSnapshot,"ProfileImageUrl");
                    BscMsc = getChildvalue(dataSnapshot, "BscMSc");
                    Year = getChildvalue(dataSnapshot, "Year");
                    Maand = getChildvalue(dataSnapshot,"Month");
                    Dag = getChildvalue(dataSnapshot, "Day");
                    int Yearint = Integer.parseInt(Year);
                    int Monthint = Integer.parseInt(Maand);
                    int Dayint = Integer.parseInt(Dag);
                    int Leeftijd = calCAge(Yearint,Monthint,Dayint);
                    Leeftijds = Integer.toString(Leeftijd);
                    Log.d("Debug", Integer.toString(Leeftijd));
                    Log.d("Debug",Key+Naam+School+Hobby1+Hobby2+Hobby3+Aboutme+Picture+Year+Maand+Dag+BscMsc);
                    CardsStudent Item = new CardsStudent(Key, Naam, School, Hobby1, Hobby2, Hobby3, Aboutme, Picture,Leeftijds, BscMsc);
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



    public static String getChildvalue (DataSnapshot dataSnapshot, String key){
        try{
            String Res = dataSnapshot.child(key).getValue().toString();
            return Res;
        }
        catch (NullPointerException e){
            return "";
        }
    }
    public int calCAge(int year, int month,int days){
        return LocalDate.now().minus(Period.of(year, month, days)).getYear();
    }
}