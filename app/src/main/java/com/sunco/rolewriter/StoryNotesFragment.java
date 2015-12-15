package com.sunco.rolewriter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Blake Martin on 12/14/2015.
 *
 */
public class StoryNotesFragment extends Fragment {
    DBHandler appDB = DBHandler.getInstance(getContext());
    EditText storyName;
    ImageView saveIc;
    EditText notes;
    FrameLayout listFrag;
    Button saveBtn;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View storyview = inflater.inflate(R.layout.fragment_story_notes,
                container, false);

        storyName = (EditText) storyview.findViewById(R.id.new_title_edit_text);
        final String storyStr = getArguments().getString("titleKey");

        storyName.setText(storyStr);
        listFrag = (FrameLayout) getActivity().findViewById(R.id.story_list_id);

        notes = (EditText) storyview.findViewById(R.id.synopsis_edit_text);
        notes.setText(getArguments().getString("notesKey"));

        ImageView closeEdit = (ImageView) storyview.findViewById(R.id.exit_button);
        closeEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);
            }
        });

        ImageView delStory = (ImageView) storyview.findViewById(R.id.erase_button);

        delStory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                List<StoryClass> storyList = appDB.getAllStories();
                                for (StoryClass s : storyList) {
                                    String storyT = s.getTitle();
                                    String delTitle = storyName.getText().toString();
                                    if (delTitle.equalsIgnoreCase(storyT)) {
                                        appDB.deleteStory(s);
                                    }
                                }
                                populateListView(listFrag);
                                getActivity().findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                // Confirmation prompt
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are you sure you want to delete this story?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        saveIc = (ImageView) storyview.findViewById(R.id.save_button);
        saveBtn = (Button) storyview.findViewById(R.id.storySaveBtn);

        saveIc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notesStr = notes.getText().toString();
                List<StoryClass> storyList = appDB.getAllStories();
                for (StoryClass s : storyList) {
                    String storyT = s.getTitle();
                    if (storyStr.equalsIgnoreCase(storyT)) {
                        s.setNotes(notesStr);

                        appDB.updateStory(s);
                    }
                }
                getActivity().findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notesStr = notes.getText().toString();
                List<StoryClass> storyList = appDB.getAllStories();
                for (StoryClass s : storyList) {
                    String storyT = s.getTitle();
                    if (storyStr.equalsIgnoreCase(storyT)) {
                        s.setNotes(notesStr);

                        appDB.updateStory(s);
                    }
                }
                getActivity().findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);
            }
        });

        return storyview;
    }
    private void populateListView(View listFrag) {

        if (listFrag != null) {
            Bundle bundle = new Bundle();
            bundle.putString("titleKey", "");
            StoryList storyListFragment = new StoryList();
            storyListFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.story_list_id, storyListFragment).commit();
        }
    }
}
