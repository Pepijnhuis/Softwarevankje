package com.example.tinder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tinder.Matches.MatchesAdapter;
import com.example.tinder.Matches.MatchesObject;
import com.example.tinder.Matches.MatchesViewHolders;

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

    //Building the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Pass the layout from messages_fragment
        //Container = viewgroup that contains the fragment layout
        //Attach to root is false
        View view = inflater.inflate(R.layout.messages_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false); //allows us to scroll freely through the recyclerView
        mRecyclerView.setHasFixedSize(true);

        //setting a layoutmanager
        mMatchesLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());//getActivity().getApplicationContext() since fragment

        //pass this layoutmanager to the recyclerview
        mRecyclerView.setLayoutManager(mMatchesLayoutManager);

        mMatchesAdapter = new MatchesAdapter(getDataSetMatches(), getActivity().getApplicationContext()); //getActivity().getApplicationContext() since fragment
        mRecyclerView.setAdapter(mMatchesAdapter);

        //adding items to recyclerview
        MatchesObject objectName = new MatchesObject("kdsfjlksfdj");
        resultsMatches.add(objectName);
        mMatchesAdapter.notifyDataSetChanged();//so recyclerview can start again and look for things that have changed
        


        return view;
    }

    private ArrayList<MatchesObject> resultsMatches= new ArrayList<MatchesObject>();
    private List<MatchesObject> getDataSetMatches() {
        return resultsMatches;
    }
}