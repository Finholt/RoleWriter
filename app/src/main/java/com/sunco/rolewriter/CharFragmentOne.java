package com.sunco.rolewriter;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
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
        final String charStr = getArguments().getString("charKey");
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

        charName.setText(charStr);

        save = (Button) charview.findViewById(R.id.char_one_save_btn);

        ImageView closeEdit = (ImageView) charview.findViewById(R.id.exit_button);
        closeEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().findViewById(R.id.character_fragment_id).setVisibility(View.INVISIBLE);
            }
        });


        ImageView delChar = (ImageView) charview.findViewById(R.id.erase_button);
        final FrameLayout listFrag = (FrameLayout) getActivity().findViewById(R.id.character_fragment_id);
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


        //Adding a new character:
        if(charName.getText().toString().equalsIgnoreCase("")){
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

                    if(newChar.equalsIgnoreCase("") || cAge.equalsIgnoreCase("")|| cLoc.equalsIgnoreCase("")
                            || cOcc.equalsIgnoreCase("") || cHeight.equalsIgnoreCase("") || cWeight.equalsIgnoreCase("")
                            || cEyeC.equalsIgnoreCase("") || cHairc.equalsIgnoreCase("") || cNation.equalsIgnoreCase("")
                            || direction.getCheckedRadioButtonId() == -1 || gender.getCheckedRadioButtonId() == -1
                            || income.getCheckedRadioButtonId() == -1) {

                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        break;
                                }
                            }
                        };
                        // Confirmation prompt
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("One or more of the require fields are empty!").setPositiveButton("Cancel", dialogClickListener)
                                .show();

                    }
                    else{
                        String cDirection = getDir();
                        String cGender = getGend();
                        String cIncome = getInc();

                        appDB.addChar(new CharacterClass(storyName, newChar, cDirection, cGender, cAge, cLoc, cOcc, cIncome,
                                cHeight, cWeight, cEyeC, cHairc, cNation, "", "", "", "", "", "", "", "", "", ""));

                        Log.v("taggy", newChar);

                        Bundle bundle = new Bundle();
                        bundle.putString("charKey", newChar);
                        bundle.putString("storyKey", storyName);
                        bundle.putString("newKey", "new");
                        CharFragmentTwo charFragmentTwo = new CharFragmentTwo();
                        charFragmentTwo.setArguments(bundle);
                        getFragmentManager().beginTransaction().replace(R.id.character_fragment_id, charFragmentTwo)
                                .addToBackStack(null)
                                .commit();
                    }
                }

            });
            // done adding new char.
        }
        else{
            /*String cAge = getArguments().getString("ageKey");
            String cLoc = getArguments().getString("locKey");
            String cOcc = getArguments().getString("occKey");
            String cHeight = getArguments().getString("heightKey");
            String cWeight = getArguments().getString("weightKey");
            String cEyeC = getArguments().getString("eyeKey");
            String cHairc = getArguments().getString("hairKey");
            String cNation = getArguments().getString("nationKey");*/

            charName.setText(getArguments().getString("charKey"));
            age.setText(getArguments().getString("ageKey"));
            loc.setText(getArguments().getString("locKey"));
            occ.setText(getArguments().getString("occKey"));
            height.setText(getArguments().getString("heightKey"));
            weight.setText(getArguments().getString("weightKey"));
            eyeC.setText(getArguments().getString("eyeKey"));
            hairC.setText(getArguments().getString("hairKey"));
            nation.setText(getArguments().getString("nationKey"));

            direction.check(Integer.valueOf(getArguments().getString("dirKey")));
            gender.check(Integer.valueOf(getArguments().getString("genderKey")));
            income.check(Integer.valueOf(getArguments().getString("incKey")));


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

                    String hardw = "";
                    String happy = "";
                    String smart = "";
                    String polite = "";
                    String selfish = "";
                    String quiet = "";
                    String brave = "";
                    String calm = "";

                    List<CharacterClass> characterList = appDB.getAllChars(storyName);
                    for (CharacterClass c : characterList) {
                        String characterN = c.getCharName();
                        if (charName.getText().toString().equalsIgnoreCase(characterN)) {
                            c.setAge(cAge);
                            c.setLocation(cLoc);
                            c.setOccupation(cOcc);
                            c.setHeight(cHeight);
                            c.setWeight(cWeight);
                            c.setEyeC(cEyeC);
                            c.setHairC(cHairc);
                            c.setNation(cNation);
                            c.setDirection(cDirection);
                            c.setGender(cGender);
                            c.setIncome(cIncome);

                            hardw = c.getHardwork();
                            happy = c.getHappy();
                            smart = c.getSmart();
                            polite = c.getPolite();
                            selfish = c.getSelfish();
                            quiet = c.getQuiet();
                            brave = c.getBrave();
                            calm = c.getCalm();

                            Log.v("taggy","brave: " + brave+ " calm: " + calm);

                            appDB.updateChar(c);
                        }
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("charKey",newChar);
                    bundle.putString("storyKey",storyName);
                    bundle.putString("newKey","existing");
                    bundle.putString("hardKey",hardw);
                    bundle.putString("happyKey",happy);
                    bundle.putString("smartKey",smart);
                    bundle.putString("politeKey",polite);
                    bundle.putString("selfishKey",selfish);
                    bundle.putString("quietKey",quiet);
                    bundle.putString("braveKey",brave);
                    bundle.putString("calmKey",calm);
                    Log.v("taggy","brave: " + brave+ " calm: " + calm);

                    CharFragmentTwo charFragmentTwo = new CharFragmentTwo();
                    charFragmentTwo.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.character_fragment_id,charFragmentTwo)
                            .addToBackStack(null)
                            .commit();

                }
            });



        }




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
