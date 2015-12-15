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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDB = appDB.getInstance(getActivity());
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View characterlistview = inflater.inflate(R.layout.fragment_character_list,
                container, false);

        //Gets the name of the story using the storyKey
        String storyName = getArguments().getString("storyKey");

        //Creates a list of characters from the database
        List<CharacterClass> characters = appDB.getAllChars(storyName);
        int characterCount = appDB.getCharCount(storyName);

        charactername = new String[characterCount];

        int i =0;
        for (CharacterClass c : characters) {
            charactername[i] = c.getCharName();
            i++;
        }

        //Calls the CharacterAdapter class to populate the ListView, passing in the array of characters
        CharacterAdapter adapter = new CharacterAdapter(this.getActivity(), charactername);
        listView = (ListView) characterlistview.findViewById(R.id.characterList);
        listView.setAdapter(adapter);

        return characterlistview;
    }
}
