package com.example.tinder.Chat;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tinder.R;

import java.util.List;

//populating all matches items
public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolders> {

    //contains information passed through ChatObject
    private List<ChatObject> chatList;
    private Context context;

    //passes information from MessagesFragment to ChatAdapter
    public ChatAdapter(List<ChatObject> matchesList, Context context){
        this.chatList = matchesList;
        this.context = context;
    }

    @Override
    public ChatViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        //this part controls the layout

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        ChatViewHolders rcv = new ChatViewHolders(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(ChatViewHolders holder, int position) {
        holder.mMessage.setText(chatList.get(position).getMessage());
        if (chatList.get(position).getCurrentUser()) {
            holder.mMessage.setGravity(Gravity.END);
            holder.mMessage.setTextColor(Color.parseColor("#404040"));
            holder.mContainer.setBackgroundColor(Color.parseColor("#F4F4F4"));
        }else{
            holder.mMessage.setGravity(Gravity.START);
            holder.mMessage.setTextColor(Color.parseColor("#FFFFFF"));
            holder.mContainer.setBackgroundColor(Color.parseColor("#2DB4C8"));
        }

    }

    @Override
    public int getItemCount() {
        return this.chatList.size();
    }
}
