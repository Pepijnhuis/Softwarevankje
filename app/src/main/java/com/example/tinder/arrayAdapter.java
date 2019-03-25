
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

        name.setText(card_item.getName());
        //image.setImageResource(R.mipmap.ic_launcher);

        return convertView;
    }

}