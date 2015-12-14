package com.sunco.rolewriter;

import android.app.FragmentTransaction;
import android.os.Bundle;

import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;


/**
 * Created by Blake Martin on 12/7/2015.
 *
 */
public class CharFragmentOne extends Fragment{
    EditText charName;
    RadioGroup direction;
    RadioGroup gender;
    EditText age;
    EditText loc;
    EditText occ;
    RadioGroup income;
    EditText height;
    EditText weight;
    EditText eyeC;
    EditText hairC;
    EditText nation;
    Button save;

    DBHandler appDB = DBHandler.getInstance(getContext());


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View charview = inflater.inflate(R.layout.fragment_char_one,
                container, false);

        final String storyName = getArguments().getString("storyKey");
        Log.v("taggy",storyName);
        List<CharacterClass> chars = appDB.getAllChars(storyName);
        int charCount = appDB.getCharCount(storyName);

        charName = (EditText) charview.findViewById(R.id.character_edit_text);
        direction = (RadioGroup) charview.findViewById(R.id.direction_radio_group);
        gender = (RadioGroup) charview.findViewById(R.id.gender_radio_group);
        age = (EditText) charview.findViewById(R.id.age_edit_text);
        loc = (EditText) charview.findViewById(R.id.location_edit_text);
        occ = (EditText) charview.findViewById(R.id.occupation_edit_text);
        income = (RadioGroup) charview.findViewById(R.id.income_radio_group);
        height = (EditText) charview.findViewById(R.id.height_edit_text);
        weight = (EditText) charview.findViewById(R.id.weight_edit_text);
        eyeC = (EditText) charview.findViewById(R.id.eye_color_edit_text);
        hairC = (EditText) charview.findViewById(R.id.hair_color_edit_text);
        nation = (EditText) charview.findViewById(R.id.nationality_edit_text);


        save = (Button) charview.findViewById(R.id.char_one_save_btn);

        //Adding a new character:
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newChar = charName.getText().toString();
                String cAge = age.getText().toString();
                String cLoc = loc.getText().toString();
                String cOcc = occ.getText().toString();
                String cHeight = height.getText().toString();
                String cWeight = weight.getText().toString();
                String cEyeC = eyeC.getText().toString();
                String cHairc = hairC.getText().toString();
                String cNation = nation.getText().toString();

                String cDirection = getDir();
                String cGender = getGend();
                String cIncome = getInc();

                appDB.addChar(new CharacterClass(storyName, newChar, cDirection, cGender, cAge, cLoc, cOcc, cIncome,
                        cHeight, cWeight, cEyeC, cHairc, cNation, "", "", "", "", "", "", "", "", ""));

                Log.v("taggy", newChar);

                Bundle bundle = new Bundle();
                bundle.putString("charKey",newChar);
                bundle.putString("storyKey",storyName);
                CharFragmentTwo charFragmentTwo = new CharFragmentTwo();
                charFragmentTwo.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.character_fragment_id,charFragmentTwo)
                        .addToBackStack(null)
                        .commit();
            }
        });



        return charview;
    }

    String getDir(){
        direction = (RadioGroup) getView().findViewById(R.id.direction_radio_group);
        int selectedDir = direction.getCheckedRadioButtonId();
        RadioButton dirBtn = (RadioButton) getView().findViewById(selectedDir);
        //String ageStr = ageBtn.getText().toString();
        //return ageStr;
        return String.valueOf(dirBtn.getId());
    }

    String getGend(){
        gender = (RadioGroup) getView().findViewById(R.id.gender_radio_group);
        int selected = gender.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) getView().findViewById(selected);
        //String ageStr = ageBtn.getText().toString();
        //return ageStr;
        return String.valueOf(btn.getId());
    }

    String getInc(){
        income = (RadioGroup) getView().findViewById(R.id.income_radio_group);
        int selected = income.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) getView().findViewById(selected);
        //String ageStr = ageBtn.getText().toString();
        //return ageStr;
        return String.valueOf(btn.getId());
    }
}
