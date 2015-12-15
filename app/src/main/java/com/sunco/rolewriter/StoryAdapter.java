package com.sunco.rolewriter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Connor on 12/8/2015.
 *
 */
public class StoryAdapter extends ArrayAdapter<String> {
    private String[] objects;

    public StoryAdapter(Context context, String[] objects) {
        super(context, R.layout.story_items, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        // Inflates view if it is null
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.story_items, null);
        }

        String i = objects[position];

        if (i != null) {
            //Finds the TextView for the story's name
            TextView sn = (TextView) v.findViewById(R.id.story_name);

            //Assigns story's name
            if(sn !=null){
                sn.setText(i);
            }
        }

        //Returns the view to the base activity
        return v;
    }
}
