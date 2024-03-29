package com.example.tinder.Cards;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tinder.Cards.CardsHouse;
import com.example.tinder.R;

import java.util.List;

public class arrayAdapterHouse extends ArrayAdapter<CardsHouse>{
    Context context;

    public arrayAdapterHouse(Context context, int recourcceId, List<CardsHouse> items){
        super(context, recourcceId, items);
        Log.d("Debug" ,"arrayAdapterStudent Called");
        Log.d("Debug" , context.toString());


    }
    public View getView(int position, View convertView, ViewGroup parent){
        Log.d("Debug","Get View Called");
        CardsHouse card_item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_house, parent, false);
        }

        //reference to xml
        TextView Name = (TextView) convertView.findViewById(R.id.cardNameHouse);
        TextView Address = (TextView) convertView.findViewById(R.id.cardHouseAddress);
        ImageView image = (ImageView) convertView.findViewById(R.id.profilePicture);
        TextView Size = (TextView) convertView.findViewById(R.id.cardHouseSize);
        TextView Rent = (TextView) convertView.findViewById(R.id.cardHouseRent);
        TextView NumberHouseMates = (TextView) convertView.findViewById(R.id.cardHOuseNumberHousemates);
        TextView AboutMe = (TextView) convertView.findViewById(R.id.cardAboutMe);
        TextView AboutmeTitle = (TextView) convertView.findViewById(R.id.AboutMeTitle);
        TextView AddressTitle = (TextView) convertView.findViewById(R.id.AddressTitle);


        //titles cards
        AboutmeTitle.setText("About us");
        AddressTitle.setText("Address");

        //get text
        Name.setText(card_item.getName());
        Address.setText(card_item.getAddress());
        Size.setText(card_item.getSize());
        Rent.setText(card_item.getRent());
        NumberHouseMates.setText(card_item.getNumberHousemates());
        AboutMe.setText(card_item.getAboutme());

        Glide.with(getContext()).load(card_item.getProfileImageUrl()).into(image);

        return convertView;
    }

}