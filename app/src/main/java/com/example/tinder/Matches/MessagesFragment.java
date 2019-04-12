package com.example.tinder.Matches;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tinder.Matches.MatchesAdapter;
import com.example.tinder.Matches.MatchesObject;
import com.example.tinder.Matches.MatchesViewHolders;
import com.example.tinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

public class MessagesFragment extends Fragment {

    //Creating a tag
    private static final String TAG = "MessagesFragment";

    //recyclerview for matches
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mMatchesAdapter;
    private RecyclerView.LayoutManager mMatchesLayoutManager;


    private String CurrentUserId;

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from messages_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.messages_fragment, container, false);

        //current user
        CurrentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("Debug, Current User", CurrentUserId);

        //recyclerView container
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false); //allows us to scroll freely through the recyclerView
        mRecyclerView.setHasFixedSize(true);

        //setting a layoutmanager
        mMatchesLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());//getActivity().getApplicationContext() since fragment

        //pass this layoutmanager to the recyclerview
        mRecyclerView.setLayoutManager(mMatchesLayoutManager);

        mMatchesAdapter = new MatchesAdapter(getDataSetMatches(), getActivity().getApplicationContext()); //getActivity().getApplicationContext() since fragment
        mRecyclerView.setAdapter(mMatchesAdapter);

        getUserMatchId();

        return view;
    }

    //look through the matches of the current user, and passing on the IDs
    private void getUserMatchId() {
        Log.d("Debug MessagesFragment", "getUsermatchID called");
        resultsMatches.clear();
        //match database
        final DatabaseReference matchDb = FirebaseDatabase.getInstance().getReference().child("Users").child(CurrentUserId).child("connections").child("matches");
        //read data matches and listen for changes, only triggers once
        matchDb.addListenerForSingleValueEvent(new ValueEventListener() {
            //if there are changes
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check if something is in there

                if (dataSnapshot.exists()){
                    //pass first match to this variable
                    for (DataSnapshot match : dataSnapshot.getChildren()){
                        FetchMatchInformation(match.getKey());
                        Log.d("Debug", "match found. MatchID = "+match.getKey());
                    }
                }
            }

            //gets called if read is cancelled
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //gather user information
    private void FetchMatchInformation(String key) {
        //user database
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Users").child(key);
        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check if something is in there
                if (dataSnapshot.exists()){
                    //get user id, name and profile picture
                    String userId = dataSnapshot.getKey();
                    String Name = "";
                    String ProfileImageUrl = "";

                    //check if name is provided
                    if (dataSnapshot.child("Name").getValue()!=null){
                        Name = dataSnapshot.child("Name").getValue().toString();
                    }

                    //check if profile image is provided
                    if (dataSnapshot.child("ProfileImageUrl").getValue()!=null){
                        ProfileImageUrl = dataSnapshot.child("ProfileImageUrl").getValue().toString();
                    }

                    //pass data inside the ChatObject so it can go in ChatAdapter and populate the

                    //adding items to recyclerview
                    MatchesObject objectName = new MatchesObject(userId, Name, ProfileImageUrl);//add
                    resultsMatches.add(objectName);
                    mMatchesAdapter.notifyDataSetChanged();//so recyclerview can start again and look for things that have changed
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private ArrayList<MatchesObject> resultsMatches= new ArrayList<MatchesObject>();
    private List<MatchesObject> getDataSetMatches() {
        return resultsMatches;
    }
}