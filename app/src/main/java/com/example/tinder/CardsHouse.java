package com.example.tinder;

import android.content.RestrictionEntry;
import android.util.Log;
import android.util.Printer;

import java.security.PrivateKey;

public class CardsHouse {
    private String userId;
    private String Name;
    private String School;
    private String NumberHousemates;
    private String Size;
    private String Rent;
    private String AboutMe;
    public CardsHouse (String userId, String name, String Rent,String Size, String NumberHousenates,String AboutMe){
        Log.d("Debug","Cards Class Called");
        this.userId = userId;
        this.Name = Name;
        this.Rent = Rent;
        this.Size = Size;
        this.NumberHousemates = NumberHousenates;
        this.AboutMe = AboutMe;
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

    public void setName(String name){
        this.Name = Name;
    }

    public String getSize(){return Size;}


    public String getRent(){return this.Rent;}

    public String getNumberHousemates(){return this.NumberHousemates;}

    public String getAboutme(){return this.AboutMe;}
}