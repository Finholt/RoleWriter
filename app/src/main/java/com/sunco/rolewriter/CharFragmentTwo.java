package com.sunco.rolewriter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Blake Martin on 12/7/2015.
 *
 */
public class CharFragmentTwo extends Fragment {
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View charview = inflater.inflate(R.layout.fragment_char_two,
                container, false);

        Button save = (Button) charview.findViewById(R.id.char_two_save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharFragmentThree charFragmentThree = new CharFragmentThree();
                getFragmentManager().beginTransaction().replace(R.id.story_fragment_id, charFragmentThree)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return charview;
    }
}