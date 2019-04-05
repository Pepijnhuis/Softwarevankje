package com.example.tinder.Cards;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tinder.Cards.CardsHouse;
import com.example.tinder.R;

import java.util.List;

public class arrayAdapterHouse extends ArrayAdapter<CardsHouse>{
    Context context;

    public arrayAdapterHouse(Context context, int recourcceId, List<CardsHouse> items){
        super(context, recourcceId, items);
        Log.d("Debug" ,"arrayAdapter Called");
        Log.d("Debug" , context.toString());


    }
    public View getView(int position, View convertView, ViewGroup parent){
        Log.d("Debug","Get View Called");
        CardsHouse card_item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemhouse, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.cardNameHouse);
        ImageView image = (ImageView) convertView.findViewById(R.id.profilePicture);
        TextView Size = (TextView) convertView.findViewById(R.id.cardHouseSize);
        TextView Rent = (TextView) convertView.findViewById(R.id.cardHouseRent);
        TextView NumberHouseMates = (TextView) convertView.findViewById(R.id.cardHOuseNumberHousemates);
        TextView AboutMe = (TextView) convertView.findViewById(R.id.cardAboutMe);
        TextView Hobbiestitle = (TextView) convertView.findViewById(R.id.Hobbiestitle);
        TextView Aboutmetitle = (TextView) convertView.findViewById(R.id.AboutMeTitle);
        Aboutmetitle.setText("About me");
        Hobbiestitle.setText("Hobbies");
        name.setText(card_item.getName());
        Size.setText(card_item.getSize());
        Rent.setText(card_item.getRent());
        NumberHouseMates.setText(card_item.getNumberHousemates());
        AboutMe.setText(card_item.getAboutme());
        //image.setImageResource(card_item.getPicture());

        return convertView;
    }

}