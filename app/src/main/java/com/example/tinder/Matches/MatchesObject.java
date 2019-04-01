package com.example.tinder.Matches;

import android.util.Log;

public class MatchesObject {

    private String userId;

    public MatchesObject (String userId){
        Log.d("Debug","MatchesObject Class Called");
        this.userId = userId;
    }
    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
}
