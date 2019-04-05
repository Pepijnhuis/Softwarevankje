package com.example.tinder.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinder.R;

//will call every id we have into item_matches
public class ChatViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {


    public ChatViewHolders(View itemView) {
        super (itemView);
        itemView.setOnClickListener(this);
    }

    //to chat
    @Override
    public void onClick (View view){
    }
}
