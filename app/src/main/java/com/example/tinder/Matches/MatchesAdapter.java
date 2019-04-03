package com.example.tinder.Matches;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tinder.R;

import java.util.List;

//populating all matches items
public class MatchesAdapter extends RecyclerView.Adapter<MatchesViewHolders> {

    //contains information passed through MatchesObject
    private List<MatchesObject> matchesList;
    private Context context;

    //passes information from MessagesFragment to MatchesAdapter
    public MatchesAdapter(List<MatchesObject> matchesList, Context context){
        this.matchesList = matchesList;
        this.context = context;
    }

    @Override
    public MatchesViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        //this part controls the layout

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matches, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        MatchesViewHolders rcv = new MatchesViewHolders(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(MatchesViewHolders holder, int position) {
        //populating the layout passed in MatchesViewHolders

        //holder contains layout item matches
        //position is at which position sthe holder is within the recyclerview
        holder.mMatchId.setText(matchesList.get(position).getUserId());
        holder.mMatchName.setText(matchesList.get(position).getName());

        //check if image is equal to default
        //if (!matchesList.get(position).getProfileImageUrl().eguals("default")){
            //Glide.with(context).load(matchesList.get(position).getProfileImageUrl()).into(holder.mMatchImage);
        //}

    }

    @Override
    public int getItemCount() {
        return this.matchesList.size();
    }
}
