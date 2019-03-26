package com.example.tinder;

import android.util.Log;

public class Cards {
    private String userId;
    private String name;
    private String School;
    private String Hobby1;
    public Cards (String userId, String name, String School,String Hobby1){
        Log.d("Debug","Cards Class Called");
        this.userId = userId;
        this.name = name;
        this.School = School;
        this.Hobby1 = Hobby1;

    }
    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSchool(){return School;}

    public void setSchool(String School){this.School = School;}

    public String getHobby1(){return this.Hobby1;}
}