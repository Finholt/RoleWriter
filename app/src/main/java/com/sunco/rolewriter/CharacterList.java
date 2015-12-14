package com.sunco.rolewriter;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


public class CharacterList extends android.support.v4.app.Fragment {
    // declare class variables
    public static final String ARG_PAGE = "Arg_Page";
    String[] charactername;
    ListView listView;
    DBHandler appDB;

    /*public static StoryListFragment newInstance (int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        StoryListFragment fragment = new StoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDB = appDB.getInstance(getActivity());
    }

    /** Called when the activity is first created. */
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View characterlistview = inflater.inflate(R.layout.fragment_character_list,
                container, false);

        String storyName = getArguments().getString("storyKey");

        List<CharacterClass> characters = appDB.getAllChars(storyName);
        int characterCount = appDB.getCharCount(storyName);

        charactername = new String[characterCount];

        int i =0;
        //String log ="";
        for (CharacterClass c : characters) {
            charactername[i] = c.getCharName();
            i++;
        }

        CharacterAdapter adapter = new CharacterAdapter(this.getActivity(), charactername);

        listView = (ListView) characterlistview.findViewById(R.id.storyList);

        listView.setAdapter(adapter);

        return characterlistview;
    }
}
