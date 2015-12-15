package com.sunco.rolewriter;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Connor on 12/8/2015.
 * 
 */
public class StoryList extends Fragment {
    // declare class variables
    public static final String ARG_PAGE = "Arg_Page";
    String[] storyname;
    ListView listView;
    DBHandler appDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDB = appDB.getInstance(getActivity());
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View storylistview = inflater.inflate(R.layout.fragment_story_list,
                container, false);

        //Gets story data from database
        List<StoryClass> stories = appDB.getAllStories();
        int storyCount = appDB.getStoryCount();
        storyname = new String[storyCount];

        int i =0;
        for (StoryClass s : stories) {
            storyname[i] = s.getTitle();
            i++;
        }

        int midPt = storyname.length /2;
        for (int j = 0; j<midPt; j++){
            String temp = storyname[j];
            storyname[j] = storyname[storyname.length-j-1];
            storyname[storyname.length-j-1] = temp;
        }

        //Calls the StoryAdapter to populate the ListView of Stories
        StoryAdapter adapter = new StoryAdapter(this.getActivity(), storyname);
        listView = (ListView) storylistview.findViewById(R.id.storyList);
        listView.setAdapter(adapter);

        return storylistview;
    }
}
