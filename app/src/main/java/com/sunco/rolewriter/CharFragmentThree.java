package com.sunco.rolewriter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Blake Martin on 12/7/2015.
 *
 */
public class CharFragmentThree extends Fragment {

    Button saveBtn;
    EditText charName;
    FrameLayout listFrag;

    DBHandler appDB = DBHandler.getInstance(getContext());

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View charview = inflater.inflate(R.layout.fragment_char_three,
                container, false);
        final View baseview = inflater.inflate(R.layout.activity_character_base,
                container, false);

        final String charStr = getArguments().getString("charKey");
        Log.v("taggy",charStr);
        final String storyName = getArguments().getString("storyKey");
        charName = (EditText) charview.findViewById(R.id.char_three_input);
        charName.setText(charStr);
        saveBtn = (Button) charview.findViewById(R.id.char_three_save_btn);
        listFrag = (FrameLayout) baseview.findViewById(R.id.character_fragment_id);
        
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


        final TextView[] interestsIcons = {(TextView) charview.findViewById(R.id.sports_button),
                (TextView) charview.findViewById(R.id.running_button),
                (TextView) charview.findViewById(R.id.yoga_button),
                (TextView) charview.findViewById(R.id.music_button),
                (TextView) charview.findViewById(R.id.writing_button),
                (TextView) charview.findViewById(R.id.reading_button),
                (TextView) charview.findViewById(R.id.photography_button),
                (TextView) charview.findViewById(R.id.art_button),
                (TextView) charview.findViewById(R.id.food_button),
                (TextView) charview.findViewById(R.id.relaxing_button),
                (TextView) charview.findViewById(R.id.hiking_button),
                (TextView) charview.findViewById(R.id.camping_button),
                (TextView) charview.findViewById(R.id.travel_button),
                (TextView) charview.findViewById(R.id.dancing_button),
                (TextView) charview.findViewById(R.id.cooking_button),
                (TextView) charview.findViewById(R.id.baking_button),
                (TextView) charview.findViewById(R.id.craft_button),
                (TextView) charview.findViewById(R.id.gardening_button),
                (TextView) charview.findViewById(R.id.hunting_button),
                (TextView) charview.findViewById(R.id.learning_button),
                (TextView) charview.findViewById(R.id.social_button),
                (TextView) charview.findViewById(R.id.sleeping_button),
                (TextView) charview.findViewById(R.id.cars_button),
                (TextView) charview.findViewById(R.id.swimming_button),
                (TextView) charview.findViewById(R.id.gaming_button)};

        for (int i = 0; i<interestsIcons.length; i++)
        {
            interestsIcons[i].setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    switch(v.getId()) {
                        case R.id.sports_button:
                            sportsG();
                            break;
                        case R.id.running_button:
                            runningG();
                            break;
                        case R.id.yoga_button:
                            yogaG();
                            break;
                        case R.id.music_button:
                            musicG();
                            break;
                        case R.id.writing_button:
                            writingG();
                            break;
                        case R.id.reading_button:
                            readingG();
                            break;
                        case R.id.photography_button:
                            photographyG();
                            break;
                        case R.id.art_button:
                            artG();
                            break;
                        case R.id.food_button:
                            foodG();
                            break;
                        case R.id.relaxing_button:
                            relaxG();
                            break;
                        case R.id.hiking_button:
                            hikingG();
                            break;
                        case R.id.camping_button:
                            campingG();
                            break;
                        case R.id.travel_button:
                            travelG();
                            break;
                        case R.id.dancing_button:
                            dancingG();
                            break;
                        case R.id.cooking_button:
                            cookingG();
                            break;
                        case R.id.baking_button:
                            bakingG();
                            break;
                        case R.id.craft_button:
                            craftG();
                            break;
                        case R.id.gardening_button:
                            gardeningG();
                            break;
                        case R.id.hunting_button:
                            huntingG();
                            break;
                        case R.id.learning_button:
                            learningG();
                            break;
                        case R.id.social_button:
                            socialG();
                            break;
                        case R.id.sleeping_button:
                            sleepingG();
                            break;
                        case R.id.cars_button:
                            carsG();
                            break;
                        case R.id.swimming_button:
                            swimmingG();
                            break;
                        case R.id.gaming_button:
                            gamingG();
                            break;
                    }
                }

            });
        }

        if(!getArguments().getString("intsKey").equalsIgnoreCase("")){

            String[] intTags;
            intTags = strToArr(getArguments().getString("intsKey"));

            for (int i =0; i<25; i++){
                interestsIcons[i].setTag(intTags[i]);
            }

            Log.v("taggy",Arrays.toString(intTags));

            sportsLoad(charview);
            yogaLoad(charview);
            runLoad(charview);
            musicLoad(charview);
            writingLoad(charview);
            readingLoad(charview);
            photoLoad(charview);
            artLoad(charview);
            foodLoad(charview);
            relaxLoad(charview);
            campLoad(charview);
            hikeLoad(charview);
            travelLoad(charview);
            danceLoad(charview);
            cookLoad(charview);
            bakeLoad(charview);
            craftLoad(charview);
            gardenLoad(charview);
            huntingLoad(charview);
            learnLoad(charview);
            socialLoad(charview);
            sleepLoad(charview);
            carsLoad(charview);
            swimLoad(charview);
            gameLoad(charview);
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String[] interestTags = new String[25];
                for (int i =0; i<25; i++){
                    interestTags[i] = interestsIcons[i].getTag().toString();
                }

                String genreStr = Arrays.toString(interestTags);
                strToArr(genreStr);
                
                    List<CharacterClass> charList = appDB.getAllChars(storyName);
                    for (CharacterClass c : charList) {
                        String charN = c.getCharName();
                        if (charStr.equalsIgnoreCase(charN)) {
                            c.setInterests(genreStr);

                            appDB.updateChar(c);

                        }
                    }

                    populateListView(listFrag);

                    getActivity().findViewById(R.id.character_fragment_id).setVisibility(View.INVISIBLE);



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

    public String[] strToArr(String toConvert){
        String[] result;
        toConvert = toConvert.substring(1, toConvert.indexOf("]"));
        result = toConvert.split(", ");
        return result;
    }

    public void sportsG() {
        if (Integer.parseInt(getView().findViewById(R.id.sports_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.sports_button).setBackgroundResource(R.drawable.sports_selected);
            getView().findViewById(R.id.sports_button).setTag(2);
        } else {
            getView().findViewById(R.id.sports_button).setBackgroundResource(R.drawable.sports);
            getView().findViewById(R.id.sports_button).setTag(1);
        }

    }
    public void runningG() {
        if (Integer.parseInt(getView().findViewById(R.id.running_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.running_button).setBackgroundResource(R.drawable.running_selected);
            getView().findViewById(R.id.running_button).setTag(2);
        } else {
            getView().findViewById(R.id.running_button).setBackgroundResource(R.drawable.running);
            getView().findViewById(R.id.running_button).setTag(1);
        }

    }
    public void yogaG() {
        if (Integer.parseInt(getView().findViewById(R.id.yoga_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.yoga_button).setBackgroundResource(R.drawable.yoga_selected);
            getView().findViewById(R.id.yoga_button).setTag(2);
        } else {
            getView().findViewById(R.id.yoga_button).setBackgroundResource(R.drawable.yoga);
            getView().findViewById(R.id.yoga_button).setTag(1);
        }

    }
    public void musicG() {
        if (Integer.parseInt(getView().findViewById(R.id.music_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.music_button).setBackgroundResource(R.drawable.music_selected);
            getView().findViewById(R.id.music_button).setTag(2);
        } else {
            getView().findViewById(R.id.music_button).setBackgroundResource(R.drawable.music);
            getView().findViewById(R.id.music_button).setTag(1);
        }

    }
    public void writingG() {
        if (Integer.parseInt(getView().findViewById(R.id.writing_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.writing_button).setBackgroundResource(R.drawable.writing_selected);
            getView().findViewById(R.id.writing_button).setTag(2);
        } else {
            getView().findViewById(R.id.writing_button).setBackgroundResource(R.drawable.writing);
            getView().findViewById(R.id.writing_button).setTag(1);
        }

    }
    public void readingG() {
        if (Integer.parseInt(getView().findViewById(R.id.reading_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.reading_button).setBackgroundResource(R.drawable.reading_selected);
            getView().findViewById(R.id.reading_button).setTag(2);
        } else {
            getView().findViewById(R.id.reading_button).setBackgroundResource(R.drawable.reading);
            getView().findViewById(R.id.reading_button).setTag(1);
        }

    }
    public void photographyG() {
        if (Integer.parseInt(getView().findViewById(R.id.photography_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.photography_button).setBackgroundResource(R.drawable.photography_selected);
            getView().findViewById(R.id.photography_button).setTag(2);
        } else {
            getView().findViewById(R.id.photography_button).setBackgroundResource(R.drawable.photography);
            getView().findViewById(R.id.photography_button).setTag(1);
        }

    }
    public void artG() {
        if (Integer.parseInt(getView().findViewById(R.id.art_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.art_button).setBackgroundResource(R.drawable.art_selected);
            getView().findViewById(R.id.art_button).setTag(2);
        } else {
            getView().findViewById(R.id.art_button).setBackgroundResource(R.drawable.art);
            getView().findViewById(R.id.art_button).setTag(1);
        }

    }
    public void foodG() {
        if (Integer.parseInt(getView().findViewById(R.id.food_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.food_button).setBackgroundResource(R.drawable.food_selected);
            getView().findViewById(R.id.food_button).setTag(2);
        } else {
            getView().findViewById(R.id.food_button).setBackgroundResource(R.drawable.food);
            getView().findViewById(R.id.food_button).setTag(1);
        }

    }
    public void relaxG() {
        if (Integer.parseInt(getView().findViewById(R.id.relaxing_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.relaxing_button).setBackgroundResource(R.drawable.relaxing_selected);
            getView().findViewById(R.id.relaxing_button).setTag(2);
        } else {
            getView().findViewById(R.id.relaxing_button).setBackgroundResource(R.drawable.relaxing );
            getView().findViewById(R.id.relaxing_button).setTag(1);
        }

    }
    public void hikingG() {
        if (Integer.parseInt(getView().findViewById(R.id.hiking_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.hiking_button).setBackgroundResource(R.drawable.hiking_selected);
            getView().findViewById(R.id.hiking_button).setTag(2);
        } else {
            getView().findViewById(R.id.hiking_button).setBackgroundResource(R.drawable.hiking);
            getView().findViewById(R.id.hiking_button).setTag(1);
        }

    }
    public void campingG() {
        if (Integer.parseInt(getView().findViewById(R.id.camping_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.camping_button).setBackgroundResource(R.drawable.camping_selected);
            getView().findViewById(R.id.camping_button).setTag(2);
        } else {
            getView().findViewById(R.id.camping_button).setBackgroundResource(R.drawable.camping);
            getView().findViewById(R.id.camping_button).setTag(1);
        }

    }
    public void travelG() {
        if (Integer.parseInt(getView().findViewById(R.id.travel_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.travel_button).setBackgroundResource(R.drawable.flying_selected);
            getView().findViewById(R.id.travel_button).setTag(2);
        } else {
            getView().findViewById(R.id.travel_button).setBackgroundResource(R.drawable.travel);
            getView().findViewById(R.id.travel_button).setTag(1);
        }

    }
    public void dancingG() {
        if (Integer.parseInt(getView().findViewById(R.id.dancing_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.dancing_button).setBackgroundResource(R.drawable.dancing_selected);
            getView().findViewById(R.id.dancing_button).setTag(2);
        } else {
            getView().findViewById(R.id.dancing_button).setBackgroundResource(R.drawable.dancing);
            getView().findViewById(R.id.dancing_button).setTag(1);
        }

    }
    public void cookingG() {
        if (Integer.parseInt(getView().findViewById(R.id.cooking_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.cooking_button).setBackgroundResource(R.drawable.cooking_selected);
            getView().findViewById(R.id.cooking_button).setTag(2);
        } else {
            getView().findViewById(R.id.cooking_button).setBackgroundResource(R.drawable.cooking);
            getView().findViewById(R.id.cooking_button).setTag(1);
        }

    }
    public void bakingG() {
        if (Integer.parseInt(getView().findViewById(R.id.baking_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.baking_button).setBackgroundResource(R.drawable.baking_selected);
            getView().findViewById(R.id.baking_button).setTag(2);
        } else {
            getView().findViewById(R.id.baking_button).setBackgroundResource(R.drawable.baking);
            getView().findViewById(R.id.baking_button).setTag(1);
        }

    }
    public void craftG() {
        if (Integer.parseInt(getView().findViewById(R.id.craft_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.craft_button).setBackgroundResource(R.drawable.crafting_selected);
            getView().findViewById(R.id.craft_button).setTag(2);
        } else {
            getView().findViewById(R.id.craft_button).setBackgroundResource(R.drawable.crafting);
            getView().findViewById(R.id.craft_button).setTag(1);
        }

    }
    public void gardeningG() {
        if (Integer.parseInt(getView().findViewById(R.id.gardening_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.gardening_button).setBackgroundResource(R.drawable.gardening_selected);
            getView().findViewById(R.id.gardening_button).setTag(2);
        } else {
            getView().findViewById(R.id.gardening_button).setBackgroundResource(R.drawable.gardeing);
            getView().findViewById(R.id.gardening_button).setTag(1);
        }

    }
    public void huntingG() {
        if (Integer.parseInt(getView().findViewById(R.id.hunting_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.hunting_button).setBackgroundResource(R.drawable.hunting_selected);
            getView().findViewById(R.id.hunting_button).setTag(2);
        } else {
            getView().findViewById(R.id.hunting_button).setBackgroundResource(R.drawable.hunting);
            getView().findViewById(R.id.hunting_button).setTag(1);
        }

    }
    public void learningG() {
        if (Integer.parseInt(getView().findViewById(R.id.learning_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.learning_button).setBackgroundResource(R.drawable.learning_selected);
            getView().findViewById(R.id.learning_button).setTag(2);
        } else {
            getView().findViewById(R.id.learning_button).setBackgroundResource(R.drawable.learning);
            getView().findViewById(R.id.learning_button).setTag(1);
        }

    }
    public void socialG() {
        if (Integer.parseInt(getView().findViewById(R.id.social_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.social_button).setBackgroundResource(R.drawable.socializing_selected);
            getView().findViewById(R.id.social_button).setTag(2);
        } else {
            getView().findViewById(R.id.social_button).setBackgroundResource(R.drawable.socializing);
            getView().findViewById(R.id.social_button).setTag(1);
        }

    }
    public void sleepingG() {
        if (Integer.parseInt(getView().findViewById(R.id.sleeping_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.sleeping_button).setBackgroundResource(R.drawable.sleeping_selected);
            getView().findViewById(R.id.sleeping_button).setTag(2);
        } else {
            getView().findViewById(R.id.sleeping_button).setBackgroundResource(R.drawable.sleeping);
            getView().findViewById(R.id.sleeping_button).setTag(1);
        }

    }
    public void carsG() {
        if (Integer.parseInt(getView().findViewById(R.id.cars_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.cars_button).setBackgroundResource(R.drawable.cars_selected);
            getView().findViewById(R.id.cars_button).setTag(2);
        } else {
            getView().findViewById(R.id.cars_button).setBackgroundResource(R.drawable.car);
            getView().findViewById(R.id.cars_button).setTag(1);
        }

    }
    public void swimmingG() {
        if (Integer.parseInt(getView().findViewById(R.id.swimming_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.swimming_button).setBackgroundResource(R.drawable.swimming_selected);
            getView().findViewById(R.id.swimming_button).setTag(2);
        } else {
            getView().findViewById(R.id.swimming_button).setBackgroundResource(R.drawable.swimming);
            getView().findViewById(R.id.swimming_button).setTag(1);
        }

    }
    public void gamingG() {
        if (Integer.parseInt(getView().findViewById(R.id.gaming_button).getTag().toString()) == 1) {
            getView().findViewById(R.id.gaming_button).setBackgroundResource(R.drawable.gaming_selected);
            getView().findViewById(R.id.gaming_button).setTag(2);
        } else {
            getView().findViewById(R.id.gaming_button).setBackgroundResource(R.drawable.gaming);
            getView().findViewById(R.id.gaming_button).setTag(1);
        }

    }

    public void sportsLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.sports_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.sports_button).setBackgroundResource(R.drawable.sports);
        }
        else
        {
            v.findViewById(R.id.sports_button).setBackgroundResource(R.drawable.sports_selected);
            v.findViewById(R.id.sports_button).setTag(2);
        }
    }

    public void yogaLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.yoga_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.yoga_button).setBackgroundResource(R.drawable.yoga);
        }
        else
        {
            v.findViewById(R.id.yoga_button).setBackgroundResource(R.drawable.yoga_selected);
            v.findViewById(R.id.yoga_button).setTag(2);
        }
    }

    public void runLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.running_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.running_button).setBackgroundResource(R.drawable.running);
        }
        else
        {
            v.findViewById(R.id.running_button).setBackgroundResource(R.drawable.running_selected);
            v.findViewById(R.id.running_button).setTag(2);
        }
    }

    public void musicLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.music_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.music_button).setBackgroundResource(R.drawable.music);
        }
        else
        {
            v.findViewById(R.id.music_button).setBackgroundResource(R.drawable.music_selected);
            v.findViewById(R.id.music_button).setTag(2);
        }
    }
    public void writingLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.writing_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.writing_button).setBackgroundResource(R.drawable.writing);
        }
        else
        {
            v.findViewById(R.id.writing_button).setBackgroundResource(R.drawable.writing_selected);
            v.findViewById(R.id.writing_button).setTag(2);
        }
    }

    public void readingLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.reading_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.reading_button).setBackgroundResource(R.drawable.reading);
        }
        else
        {
            v.findViewById(R.id.reading_button).setBackgroundResource(R.drawable.reading_selected);
            v.findViewById(R.id.reading_button).setTag(2);
        }
    }


    public void photoLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.photography_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.photography_button).setBackgroundResource(R.drawable.photography);
        }
        else
        {
            v.findViewById(R.id.photography_button).setBackgroundResource(R.drawable.photography_selected);
            v.findViewById(R.id.photography_button).setTag(2);
        }
    }

    public void artLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.art_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.art_button).setBackgroundResource(R.drawable.art);
        }
        else
        {
            v.findViewById(R.id.art_button).setBackgroundResource(R.drawable.art_selected);
            v.findViewById(R.id.art_button).setTag(2);
        }
    }


    public void foodLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.food_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.food_button).setBackgroundResource(R.drawable.food);
        }
        else
        {
            v.findViewById(R.id.food_button).setBackgroundResource(R.drawable.food_selected);
            v.findViewById(R.id.food_button).setTag(2);
        }
    }


    public void relaxLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.relaxing_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.relaxing_button).setBackgroundResource(R.drawable.relaxing);
        }
        else
        {
            v.findViewById(R.id.relaxing_button).setBackgroundResource(R.drawable.relaxing_selected);
            v.findViewById(R.id.relaxing_button).setTag(2);
        }
    }


    public void campLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.camping_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.camping_button).setBackgroundResource(R.drawable.camping);
        }
        else
        {
            v.findViewById(R.id.camping_button).setBackgroundResource(R.drawable.camping_selected);
            v.findViewById(R.id.camping_button).setTag(2);
        }
    }


    public void hikeLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.hiking_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.hiking_button).setBackgroundResource(R.drawable.hiking);
        }
        else
        {
            v.findViewById(R.id.hiking_button).setBackgroundResource(R.drawable.hiking_selected);
            v.findViewById(R.id.hiking_button).setTag(2);
        }
    }


    public void travelLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.travel_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.travel_button).setBackgroundResource(R.drawable.travel);
        }
        else
        {
            v.findViewById(R.id.travel_button).setBackgroundResource(R.drawable.flying_selected);
            v.findViewById(R.id.travel_button).setTag(2);
        }
    }


    public void danceLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.dancing_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.dancing_button).setBackgroundResource(R.drawable.dancing);
        }
        else
        {
            v.findViewById(R.id.dancing_button).setBackgroundResource(R.drawable.dancing_selected);
            v.findViewById(R.id.dancing_button).setTag(2);
        }
    }


    public void cookLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.cooking_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.cooking_button).setBackgroundResource(R.drawable.cooking);
        }
        else
        {
            v.findViewById(R.id.cooking_button).setBackgroundResource(R.drawable.cooking_selected);
            v.findViewById(R.id.cooking_button).setTag(2);
        }
    }


    public void bakeLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.baking_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.baking_button).setBackgroundResource(R.drawable.baking);
        }
        else
        {
            v.findViewById(R.id.baking_button).setBackgroundResource(R.drawable.baking_selected);
            v.findViewById(R.id.baking_button).setTag(2);
        }
    }


    public void craftLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.craft_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.craft_button).setBackgroundResource(R.drawable.crafting);
        }
        else
        {
            v.findViewById(R.id.craft_button).setBackgroundResource(R.drawable.crafting_selected);
            v.findViewById(R.id.craft_button).setTag(2);
        }
    }


    public void gardenLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.gardening_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.gardening_button).setBackgroundResource(R.drawable.gardeing);
        }
        else
        {
            v.findViewById(R.id.gardening_button).setBackgroundResource(R.drawable.gardening_selected);
            v.findViewById(R.id.gardening_button).setTag(2);
        }
    }

    public void huntingLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.hunting_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.hunting_button).setBackgroundResource(R.drawable.hunting);
        }
        else
        {
            v.findViewById(R.id.hunting_button).setBackgroundResource(R.drawable.hunting_selected);
            v.findViewById(R.id.hunting_button).setTag(2);
        }
    }


    public void learnLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.learning_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.learning_button).setBackgroundResource(R.drawable.learning);
        }
        else
        {
            v.findViewById(R.id.learning_button).setBackgroundResource(R.drawable.learning_selected);
            v.findViewById(R.id.learning_button).setTag(2);
        }
    }


    public void socialLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.social_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.social_button).setBackgroundResource(R.drawable.socializing);
        }
        else
        {
            v.findViewById(R.id.social_button).setBackgroundResource(R.drawable.socializing_selected);
            v.findViewById(R.id.social_button).setTag(2);
        }
    }


    public void sleepLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.sleeping_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.sleeping_button).setBackgroundResource(R.drawable.sleeping);
        }
        else
        {
            v.findViewById(R.id.sleeping_button).setBackgroundResource(R.drawable.sleeping_selected);
            v.findViewById(R.id.sleeping_button).setTag(2);
        }
    }


    public void carsLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.cars_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.cars_button).setBackgroundResource(R.drawable.car);
        }
        else
        {
            v.findViewById(R.id.cars_button).setBackgroundResource(R.drawable.cars_selected);
            v.findViewById(R.id.cars_button).setTag(2);
        }
    }


    public void swimLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.swimming_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.swimming_button).setBackgroundResource(R.drawable.swimming);
        }
        else
        {
            v.findViewById(R.id.swimming_button).setBackgroundResource(R.drawable.swimming_selected);
            v.findViewById(R.id.swimming_button).setTag(2);
        }
    }


    public void gameLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.gaming_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.gaming_button).setBackgroundResource(R.drawable.gaming);
        }
        else
        {
            v.findViewById(R.id.gaming_button).setBackgroundResource(R.drawable.gaming_selected);
            v.findViewById(R.id.gaming_button).setTag(2);
        }
    }

}