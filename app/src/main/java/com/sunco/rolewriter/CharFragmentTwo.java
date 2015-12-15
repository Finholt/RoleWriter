package com.sunco.rolewriter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class CharFragmentTwo extends Fragment {

    RadioGroup hardw, happy, smart, polite, selfish, quiet, brave, calm;
    DBHandler appDB = DBHandler.getInstance(getContext());
    EditText charName;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View charview = inflater.inflate(R.layout.fragment_char_two,
                container, false);

        final String charStr = getArguments().getString("charKey");
        final String storyName = getArguments().getString("storyKey");
        charName = (EditText) charview.findViewById(R.id.char_two_input);
        charName.setText(charStr);

        ImageView closeEdit = (ImageView) charview.findViewById(R.id.exit_button);
        closeEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().findViewById(R.id.character_fragment_id).setVisibility(View.INVISIBLE);
            }
        });

        //Deletes the selected character
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


        hardw = (RadioGroup) charview.findViewById(R.id.one_radio_group);
        happy = (RadioGroup) charview.findViewById(R.id.two_radio_group);
        smart = (RadioGroup) charview.findViewById(R.id.three_radio_group);
        polite = (RadioGroup) charview.findViewById(R.id.four_radio_group);
        selfish = (RadioGroup) charview.findViewById(R.id.five_radio_group);
        quiet = (RadioGroup) charview.findViewById(R.id.six_radio_group);
        brave = (RadioGroup) charview.findViewById(R.id.seven_radio_group);
        calm = (RadioGroup) charview.findViewById(R.id.eight_radio_group);

        if(getArguments().getString("newKey").equalsIgnoreCase("existing"))
        {
            hardw.check(Integer.valueOf(getArguments().getString("hardKey")));
            happy.check(Integer.valueOf(getArguments().getString("happyKey")));
            smart.check(Integer.valueOf(getArguments().getString("smartKey")));
            polite.check(Integer.valueOf(getArguments().getString("politeKey")));
            selfish.check(Integer.valueOf(getArguments().getString("selfishKey")));
            quiet.check(Integer.valueOf(getArguments().getString("quietKey")));
            brave.check(Integer.valueOf(getArguments().getString("braveKey")));
            calm.check(Integer.valueOf(getArguments().getString("calmKey")));
        }

        Button save = (Button) charview.findViewById(R.id.char_two_save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(hardw.getCheckedRadioButtonId() == -1 || happy.getCheckedRadioButtonId() == -1 ||
                        smart.getCheckedRadioButtonId() == -1 || polite.getCheckedRadioButtonId() == -1 ||
                        selfish.getCheckedRadioButtonId() == -1 || quiet.getCheckedRadioButtonId() == -1 ||
                        brave.getCheckedRadioButtonId() == -1 || calm.getCheckedRadioButtonId() == -1) {

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
                    String interests = "";
                    List<CharacterClass> charList = appDB.getAllChars(storyName);
                    for (CharacterClass c : charList) {
                        String charN = c.getCharName();
                        if (charStr.equalsIgnoreCase(charN)) {
                            String hardw = getHardw();
                            String happy = getHappy();
                            String smart = getSmart();
                            String polite = getPolite();
                            String selfish = getSelfish();
                            String quiet = getQuiet();
                            String brave = getBrave();
                            String calm = getCalm();

                            c.setHardwork(hardw);
                            c.setHappy(happy);
                            c.setSmart(smart);
                            c.setPolite(polite);
                            c.setSelfish(selfish);
                            c.setQuiet(quiet);
                            c.setBrave(brave);
                            c.setCalm(calm);

                            interests = c.getInterests();

                            appDB.updateChar(c);
                        }
                    }

                    String charTxt = charName.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString("charKey",charTxt);
                    bundle.putString("storyKey",storyName);
                    bundle.putString("intsKey",interests);
                    CharFragmentThree charFragmentThree = new CharFragmentThree();
                    charFragmentThree.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.character_fragment_id, charFragmentThree)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        ImageView saveIc = (ImageView) charview.findViewById(R.id.save_button);
        saveIc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(hardw.getCheckedRadioButtonId() == -1 || happy.getCheckedRadioButtonId() == -1 ||
                        smart.getCheckedRadioButtonId() == -1 || polite.getCheckedRadioButtonId() == -1 ||
                        selfish.getCheckedRadioButtonId() == -1 || quiet.getCheckedRadioButtonId() == -1 ||
                        brave.getCheckedRadioButtonId() == -1 || calm.getCheckedRadioButtonId() == -1) {

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
                    String interests = "";
                    List<CharacterClass> charList = appDB.getAllChars(storyName);
                    for (CharacterClass c : charList) {
                        String charN = c.getCharName();
                        if (charStr.equalsIgnoreCase(charN)) {
                            String hardw = getHardw();
                            String happy = getHappy();
                            String smart = getSmart();
                            String polite = getPolite();
                            String selfish = getSelfish();
                            String quiet = getQuiet();
                            String brave = getBrave();
                            String calm = getCalm();

                            c.setHardwork(hardw);
                            c.setHappy(happy);
                            c.setSmart(smart);
                            c.setPolite(polite);
                            c.setSelfish(selfish);
                            c.setQuiet(quiet);
                            c.setBrave(brave);
                            c.setCalm(calm);

                            interests = c.getInterests();

                            appDB.updateChar(c);
                        }
                    }
                }
            }
        });

        return charview;
    }

    String getHardw(){
        hardw = (RadioGroup) getView().findViewById(R.id.one_radio_group);
        int selected = hardw.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) getView().findViewById(selected);
        //String ageStr = ageBtn.getText().toString();
        //return ageStr;
        return String.valueOf(btn.getId());
    }

    String getHappy(){
        happy = (RadioGroup) getView().findViewById(R.id.two_radio_group);
        int selected = happy.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) getView().findViewById(selected);
        //String ageStr = ageBtn.getText().toString();
        //return ageStr;
        return String.valueOf(btn.getId());
    }

    String getSmart(){
        smart = (RadioGroup) getView().findViewById(R.id.three_radio_group);
        int selected = smart.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) getView().findViewById(selected);
        //String ageStr = ageBtn.getText().toString();
        //return ageStr;
        return String.valueOf(btn.getId());
    }

    String getPolite(){
        polite = (RadioGroup) getView().findViewById(R.id.four_radio_group);
        int selected = polite.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) getView().findViewById(selected);
        //String ageStr = ageBtn.getText().toString();
        //return ageStr;
        return String.valueOf(btn.getId());
    }

    String getSelfish(){
        selfish = (RadioGroup) getView().findViewById(R.id.five_radio_group);
        int selected = selfish.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) getView().findViewById(selected);
        //String ageStr = ageBtn.getText().toString();
        //return ageStr;
        return String.valueOf(btn.getId());
    }

    String getQuiet(){
        quiet = (RadioGroup) getView().findViewById(R.id.six_radio_group);
        int selected = quiet.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) getView().findViewById(selected);
        //String ageStr = ageBtn.getText().toString();
        //return ageStr;
        return String.valueOf(btn.getId());
    }

    String getBrave(){
        brave = (RadioGroup) getView().findViewById(R.id.seven_radio_group);
        int selected = brave.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) getView().findViewById(selected);
        //String ageStr = ageBtn.getText().toString();
        //return ageStr;
        return String.valueOf(btn.getId());
    }

    String getCalm(){
        calm = (RadioGroup) getView().findViewById(R.id.eight_radio_group);
        int selected = calm.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) getView().findViewById(selected);
        //String ageStr = ageBtn.getText().toString();
        //return ageStr;
        return String.valueOf(btn.getId());
    }

    private void populateListView(View listFrag) {
        //Generates/refreshes the Character ListView
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