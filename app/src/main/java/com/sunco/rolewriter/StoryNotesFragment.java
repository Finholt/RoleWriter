package com.sunco.rolewriter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Blake Martin on 12/14/2015.
 */
public class StoryNotesFragment extends Fragment {
    DBHandler appDB = DBHandler.getInstance(getContext());
    EditText storyName;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View storyview = inflater.inflate(R.layout.fragment_story_notes,
                container, false);

        return storyview;
    }
}
