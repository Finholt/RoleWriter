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
 */
public class StoryAdapter extends ArrayAdapter<String> {
    // declaring our ArrayList of items
    private String[] objects;
    private Context mContext;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    public StoryAdapter(Context context, String[] objects) {
        super(context, R.layout.story_items, objects);
        this.objects = objects;

        LayoutInflater inflater = LayoutInflater.from(context);
        mContext = context;
    }

    /*
	 * we are overriding the getView method here - this is what defines how each
	 * list item will look.
	 */
    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.story_items, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */

        String i = objects[position];

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView sn = (TextView) v.findViewById(R.id.story_name);
            //TextView eb = (TextView) v.findViewById(R.id.edit_button);
            //TextView nb = (TextView) v.findViewById(R.id.notes_button);
            //TextView db = (TextView) v.findViewById(R.id.delete_button);

            // check to see if each individual textview is null.
            // if not, assign some text!
            if(sn !=null){
                sn.setText(i);
            }
            /*if (tt != null){
                tt.setText("Name: ");
            }
            if (ttd != null){
                ttd.setText(i.getName());
            }
            if (mt != null){
                mt.setText("Price: ");
            }
            if (mtd != null){
                mtd.setText("$" + i.getPrice());
            }
            if (bt != null){
                bt.setText("Details: ");
            }
            if (btd != null){
                btd.setText(i.getDetails());
            }*/
        }

        // the view must be returned to our activity
        return v;

    }
}
