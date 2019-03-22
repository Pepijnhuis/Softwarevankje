package com.example.tinder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class SlideFragment extends Fragment {
    private String UserID;
    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;
    private FirebaseAuth mAuth;
    private DatabaseReference CardsRefrence;
    //Creating a tag
    private static final String TAG = "SlideFragment";

    public SlideFragment(){

    }

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from slide_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.slide_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();


        ((MainNavigation.class)getActivity()).GenerateCards();


        checkUserSex();
        MainNavigation.GenerateCards();

        //Make the array of cards
        al= new ArrayList<>();
        al.add("php");

        //Make the cards object
        //getActivity() because we're working in a Fragment
        arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.item, R.id.helloText, al );

        //Something to do with the Swipecards plugin from github:  https://github.com/Diolor/Swipecards
        //view.findViewById because we're working in a Fragment
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) view.findViewById(R.id.frameSlide);





 }