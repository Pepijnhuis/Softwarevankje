package com.example.tinder.Chat;

import android.util.Log;

public class ChatObject {
    private String message;
    private Boolean currentUser;

    public ChatObject(String message, Boolean currentUser){
        this.message = message;
        this.currentUser = currentUser;

    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String userId){
        this.message = userId;
    }

    public Boolean getCurrentUser(){
        return currentUser;
    }
    public void setCurrentUser(Boolean currentUser){
        this.currentUser = currentUser;
    }

}
