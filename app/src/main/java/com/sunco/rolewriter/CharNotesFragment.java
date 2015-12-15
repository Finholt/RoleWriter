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
import android.widget.RadioGroup;

import java.util.List;

/**
 * Created by Connor Finholt on 12/14/2015.
 *
 */
public class CharNotesFragment extends Fragment {
    DBHandler appDB = DBHandler.getInstance(getContext());
    EditText charName;
    EditText notes;
    Button saveBtn;
    ImageView saveIc;
    FrameLayout listFrag;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View charview = inflater.inflate(R.layout.fragment_char_notes,
                container, false);

        listFrag = (FrameLayout) getActivity().findViewById(R.id.character_fragment_id);
        charName = (EditText) charview.findViewById(R.id.character_edit_text);
        final String charStr = getArguments().getString("charKey");
        final String storyName = getArguments().getString("storyKey");

        charName.setText(charStr);

        notes = (EditText) charview.findViewById(R.id.synopsis_edit_text);
        notes.setText(getArguments().getString("notesKey"));

        saveBtn = (Button) charview.findViewById(R.id.charSaveBtn);
        saveIc = (ImageView) charview.findViewById(R.id.save_button);

        ImageView closeEdit = (ImageView) charview.findViewById(R.id.exit_button);
        closeEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().findViewById(R.id.character_fragment_id).setVisibility(View.INVISIBLE);
            }
        });

        ImageView delChar = (ImageView) charview.findViewById(R.id.erase_button);
        delChar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                List<CharacterClass> charList = appDB.getAllChars(storyName);
                                for (CharacterClass c : charList) {
                                    String charN = c.getCharName();
                                    if (charName.getText().toString().equalsIgnoreCase(charN)) {
                                        appDB.deleteChar(c);
                                    }
                                }
                                populateListView(listFrag);
                                getActivity().findViewById(R.id.character_fragment_id).setVisibility(View.INVISIBLE);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                // Confirmation prompt
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are you sure you want to delete this character?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notesStr = notes.getText().toString();
                List<CharacterClass> charList = appDB.getAllChars(storyName);
                for (CharacterClass c : charList) {
                    String charN = c.getCharName();
                    if (charStr.equalsIgnoreCase(charN)) {
                        c.setNotes(notesStr);

                        appDB.updateChar(c);
                    }
                }
                populateListView(listFrag);
                getActivity().findViewById(R.id.character_fragment_id).setVisibility(View.INVISIBLE);
            }
        });

        saveIc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notesStr = notes.getText().toString();
                List<CharacterClass> charList = appDB.getAllChars(storyName);
                for (CharacterClass c : charList) {
                    String charN = c.getCharName();
                    if (charStr.equalsIgnoreCase(charN)) {
                        c.setNotes(notesStr);

                        appDB.updateChar(c);
                    }
                }
            }
        });

        return charview;
    }

    private void populateListView(View listFrag) {

        String storyName = getArguments().getString("storyKey");
        if (listFrag != null) {
            Bundle bundle = new Bundle();
            bundle.putString("titleKey", "");
            bundle.putString("storyKey", storyName);
            CharacterList characterListFragment = new CharacterList();
            characterListFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.character_list_id, characterListFragment).commit();
        }
    }
}
