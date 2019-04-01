package com.example.tinder;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchesViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView mMatchId, mMatchName;
    public ImageView mMatchImage;
    public MatchesViewHolders(View itemView) {
        super (itemView);
        itemView.setOnClickListener(this);

        //mMatchId = (TextView) itemView.findViewById(R.id.MatchId);
        //mMatchName = (TextView) itemView.findViewById(R.id.MatchName);
        //mMatchImage = (ImageView) itemView.findViewById(R.id.MatchImage);
    }

    @Override
    public void onClick (View view){
        Intent intent = new Intent(view.getContext(),Chat.class);
        //moving the information we need
        Bundle b = new Bundle();
        b.putString("matchId",mMatchId.getText().toString());
        intent.putExtras(b);
        view.getContext().startActivity(intent);
    }
}
