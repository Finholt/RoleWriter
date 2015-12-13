package com.sunco.rolewriter;

import android.app.FragmentTransaction;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;


/**
 * Created by Blake Martin on 12/7/2015.
 *
 */
public class CharFragmentOne extends Fragment{
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View charview = inflater.inflate(R.layout.fragment_char_one,
                container, false);

        Button save = (Button) charview.findViewById(R.id.char_one_save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharFragmentTwo charFragmentTwo = new CharFragmentTwo();
                getFragmentManager().beginTransaction().replace(R.id.story_fragment_id,charFragmentTwo)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return charview;
    }
}
