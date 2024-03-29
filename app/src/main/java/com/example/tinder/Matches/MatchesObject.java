package com.example.tinder.Matches;

import android.util.Log;

public class MatchesObject {

    private String userId;
    private String Name;
    private String ProfileImageUrl;

    public MatchesObject (String userId, String Name, String ProfileImageUrl){ //add ,
        this.userId = userId;
        this.Name = Name;
        this.ProfileImageUrl = ProfileImageUrl;
        Log.d("Debug MatchesObject",Name);
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getName(){
        return Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }

    public String getProfileImageUrl(){
        return ProfileImageUrl;
    }
    public void setProfileImageUrl(String ProfileImageUrl){
        this.ProfileImageUrl = ProfileImageUrl;
    }
}
