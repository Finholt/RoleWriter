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
 */
public class CharNotesFragment extends Fragment {
    DBHandler appDB = DBHandler.getInstance(getContext());
    EditText charName;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View charview = inflater.inflate(R.layout.fragment_char_notes,
                container, false);

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
