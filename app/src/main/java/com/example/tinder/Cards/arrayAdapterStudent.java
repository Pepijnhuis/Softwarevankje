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
import com.example.tinder.R;

import org.w3c.dom.Text;

import java.util.List;

public class arrayAdapterStudent extends ArrayAdapter<CardsStudent>{
    Context context;

    public arrayAdapterStudent(Context context, int recourcceId, List<CardsStudent> items){
        super(context, recourcceId, items);
        Log.d("Debug" ,"arrayAdapterStudent Called");
        Log.d("Debug" , context.toString());


    }
    public View getView(int position, View convertView, ViewGroup parent){
        Log.d("Debug","Get View Called");
        CardsStudent card_item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_student, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.cardNameStudent);
        TextView School = (TextView) convertView.findViewById(R.id.cardUniversity);
        TextView Bachelormaster = (TextView) convertView.findViewById(R.id.cardBachelorMaster);
        TextView Hobby1 = (TextView) convertView.findViewById(R.id.cardHobby1);
        TextView Hobby2 = (TextView) convertView.findViewById(R.id.cardHobby2);
        TextView Hobby3 = (TextView) convertView.findViewById(R.id.cardHobby3);
        TextView AboutMe = (TextView) convertView.findViewById(R.id.cardAboutMe);
        TextView Hobbiestitle = (TextView) convertView.findViewById(R.id.Hobbiestitle);
        TextView Aboutmetitle = (TextView) convertView.findViewById(R.id.AboutMeTitle);
        ImageView image = (ImageView)  convertView.findViewById(R.id.profilePicture);

        //titles
        Aboutmetitle.setText("About me");
        Hobbiestitle.setText("Hobbies");

        //get information
        name.setText(card_item.getName()+ ", " + card_item.getLeeftijd());
        School.setText(card_item.getSchool());
        Bachelormaster.setText(card_item.getBachelormaster());
        Hobby1.setText(card_item.getHobby1());
        Hobby2.setText(card_item.getHobby2());
        Hobby3.setText(card_item.getHobby3());
        AboutMe.setText(card_item.getAboutme());

        Glide.with(getContext()).load(card_item.getProfileImageUrl()).into(image);




        //image.setImageResource(R.mipmap.ic_launcher);

        return convertView;
    }

}
