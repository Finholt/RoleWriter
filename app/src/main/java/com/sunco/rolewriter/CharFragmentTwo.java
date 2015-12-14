package com.sunco.rolewriter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

        Button save = (Button) charview.findViewById(R.id.char_two_save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                        appDB.updateChar(c);
                    }
                }

                String charTxt = charName.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("charKey",charTxt);
                bundle.putString("storyKey",storyName);
                CharFragmentThree charFragmentThree = new CharFragmentThree();
                charFragmentThree.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.character_fragment_id, charFragmentThree)
                        .addToBackStack(null)
                        .commit();
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
}