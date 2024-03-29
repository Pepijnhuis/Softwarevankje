package com.example.tinder.Chat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tinder.LoginActivity;
import com.example.tinder.MainNavigation.MainNavigationHouse;
import com.example.tinder.MainNavigation.MainNavigationStudent;
import com.example.tinder.Matches.MatchesAdapter;
import com.example.tinder.Matches.MatchesObject;
import com.example.tinder.R;
import com.example.tinder.Trash.MainNavigation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mChatAdapter;
    private RecyclerView.LayoutManager mChatLayoutManager;

    private EditText mSendEditText;
    private Button mSendButton;

    private String currentUserId, matchId, chatId, userRegistration;

    DatabaseReference mDatabaseUser, mDatabaseChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        TextView Naam = (TextView) findViewById(R.id.nameChat);
        Naam.setText(getIntent().getExtras().getString("Naam"));

        matchId = getIntent().getExtras().getString("matchId");



        //current user
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("Debug, Current User", currentUserId+"  "+ matchId);


        //probleem inloggen vanwege house/student in verschillend sub mappen in database??
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId).child("connections").child("matches").child(matchId).child("ChatId");

        mDatabaseChat = FirebaseDatabase.getInstance().getReference().child("Chat");

        getChatId();

        //recyclerView container
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false); //allows us to scroll freely through the recyclerView
        mRecyclerView.setHasFixedSize(false);

        //setting a layoutmanager
        mChatLayoutManager = new LinearLayoutManager(ChatActivity.this);//getActivity().getApplicationContext() since fragment

        //pass this layoutmanager to the recyclerview
        mRecyclerView.setLayoutManager(mChatLayoutManager);

        mChatAdapter = new ChatAdapter(getDataSetChat(), ChatActivity.this); //getActivity().getApplicationContext() since fragment
        mRecyclerView.setAdapter(mChatAdapter);

        mSendEditText = findViewById(R.id.message);
        mSendButton = findViewById(R.id.send);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String sendMessageText = mSendEditText.getText().toString();

        if (!sendMessageText.isEmpty()) { //make sure no empty messages can be send
            DatabaseReference newMessageDb = mDatabaseChat.push();

            Log.d("Debug", sendMessageText);

            Map newMessage = new HashMap();
            newMessage.put("createdByUser", currentUserId);
            newMessage.put("text", sendMessageText);

            newMessageDb.setValue(newMessage);
        }
        mSendEditText.setText(null);
    }


    //Main navigation button
    public void goToMainNavigation(View view) {
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (currentUserId != null) {
            Log.d("Debug", currentUserId);
            FirebaseDatabase.getInstance();
            DatabaseReference UserDB = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
            UserDB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    userRegistration = dataSnapshot.child("Register").getValue().toString();
                    Log.d("Debug", userRegistration);
                    switch (userRegistration) {
                        case "Student":
                            Intent intent = new Intent(ChatActivity.this, MainNavigationStudent.class);
                            startActivity(intent);
                            break;
                        case "House":
                            Intent intent2 = new Intent(ChatActivity.this, MainNavigationHouse.class);
                            startActivity(intent2);
                            break;
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void getChatId(){
        mDatabaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    chatId = dataSnapshot.getValue().toString();
                    Log.d("Debug messages", chatId);
                    mDatabaseChat = mDatabaseChat.child(chatId);
                    getChatMessages();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getChatMessages() {
        mDatabaseChat.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
                    String message = null;
                    String createdByUser = null;

                    if (dataSnapshot.child("text").getValue() != null) {
                        message = dataSnapshot.child("text").getValue().toString();
                    }
                    if (dataSnapshot.child("createdByUser").getValue() != null) {
                        createdByUser = dataSnapshot.child("createdByUser").getValue().toString();
                    }

                    if (message != null && createdByUser != null) {
                        Boolean currentUserBoolean = false;
                        if (createdByUser.equals(currentUserId)) {
                            currentUserBoolean = true;
                        }
                        ChatObject newMessage = new ChatObject(message, currentUserBoolean);
                        resultsChat.add(newMessage);
                        mChatAdapter.notifyDataSetChanged();
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

    private ArrayList<ChatObject> resultsChat= new ArrayList<ChatObject>();
    private List<ChatObject> getDataSetChat() {
        return resultsChat;
    }
}
