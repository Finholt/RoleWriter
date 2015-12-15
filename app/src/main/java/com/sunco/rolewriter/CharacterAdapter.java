package com.sunco.rolewriter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Connor on 12/13/2015.
 *
 */
public class CharacterAdapter extends ArrayAdapter<String> {
    private String[] objects;

    public CharacterAdapter(Context context, String[] objects) {
        super(context, R.layout.character_items, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        // Inflates view if it is null
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.character_items, null);
        }

        String i = objects[position];

        if (i != null) {
            //Finds the TextView for the character's name
            TextView cn = (TextView) v.findViewById(R.id.character_name);

            //Assigns character's name
            if(cn !=null){
                cn.setText(i);
            }
        }

        //Returns the view to the base activity
        return v;

    }
}
