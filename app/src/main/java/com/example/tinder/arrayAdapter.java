
package com.example.tinder;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class arrayAdapter extends ArrayAdapter<Cards>{
    Context context;

    public arrayAdapter(Context context, int recourcceId, List<Cards> items){
        super(context, recourcceId, items);
        Log.d("Debug" ,"arrayAdapter Called");
        Log.d("Debug" , context.toString());


    }
    public View getView(int position, View convertView, ViewGroup parent){
        Log.d("Debug","Get View Called");
        com.example.tinder.Cards card_item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.cardNameStudent);
        //ImageView image = (ImageView) convertView.findViewById(R.id.imageincard);
        TextView School = (TextView) convertView.findViewById(R.id.cardUniversity);
        TextView Hobby1 = (TextView) convertView.findViewById(R.id.cardHobby1);
        TextView Hobby2 = (TextView) convertView.findViewById(R.id.cardHobby2);
        TextView Hobby3 = (TextView) convertView.findViewById(R.id.cardHobby3);
        TextView AboutMe = (TextView) convertView.findViewById(R.id.cardAboutMe);
        TextView Hobbiestitle = (TextView) convertView.findViewById(R.id.Hobbiestitle);
        Hobbiestitle.setText("Hobbies");
        name.setText(card_item.getName());
        School.setText(card_item.getSchool());
        Hobby1.setText(card_item.getHobby1());
        Hobby2.setText(card_item.getHobby2());
        Hobby3.setText(card_item.getHobby3());
        AboutMe.setText(card_item.getAboutme());




        //image.setImageResource(R.mipmap.ic_launcher);

        return convertView;
    }

}