package com.example.tinder.Cards;

import android.util.Log;

public class CardsStudent {
    private String userId;
    private String name;
    private String School;
    private String Hobby1;
    private String Hobby2;
    private String Hobby3;
    private String AboutMe;
    private String ProfileImageUrl;
    private String Leeftijd;
    private String Bachelormaster;
    public CardsStudent(String userId, String name, String School, String Hobby1, String Hobby2, String Hobby3, String AboutMe, String ProfileImageUrl, String Leeftijd, String Bachelormaster){
        Log.d("Debug","CardsStudent Class Called");
        this.userId = userId;
        this.name = name;
        this.School = School;
        this.Hobby1 = Hobby1;
        this.Hobby2 = Hobby2;
        this.Hobby3 = Hobby3;
        this.AboutMe = AboutMe;
        this.ProfileImageUrl = ProfileImageUrl;
        this.Leeftijd = Leeftijd;
        this.Bachelormaster = Bachelormaster;
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

    public String getHobby2(){return this.Hobby2;}

    public String getHobby3(){return this.Hobby3;}
    public String getAboutme(){return this.AboutMe;}
    public String getProfileImageUrl(){return this.ProfileImageUrl;}
    public String getLeeftijd(){return this.Leeftijd;}
    public String getBachelormaster(){return this.Bachelormaster;};
}