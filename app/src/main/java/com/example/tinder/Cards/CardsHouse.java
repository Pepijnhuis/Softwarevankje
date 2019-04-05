package com.example.tinder.Cards;

import android.content.RestrictionEntry;
import android.util.Log;
import android.util.Printer;

import java.security.PrivateKey;

public class CardsHouse {
    private String userId;
    private String Name;
    private String Address;
    private String NumberHousemates;
    private String Size;
    private String Rent;
    private String AboutMe;
    private String Picture;
    public CardsHouse (String userId, String Address, String name, String Rent,String Size, String NumberHousenates,String AboutMe, String Picture){
        Log.d("Debug","CardsStudent Class Called");
        this.userId = userId;
        this.Address = Address;
        this.Name = Name;
        this.Rent = Rent;
        this.Size = Size;
        this.NumberHousemates = NumberHousenates;
        this.AboutMe = AboutMe;
        this.Picture = Picture;
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

    public String getAddress(){return Address;}

    public String getSize(){return Size;}


    public String getRent(){return this.Rent;}

    public String getNumberHousemates(){return this.NumberHousemates;}

    public String getAboutme(){return this.AboutMe;}
    public String getProfileImageUrl(){return this.Picture;}
}