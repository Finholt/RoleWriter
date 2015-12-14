package com.sunco.rolewriter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.FragmentManager;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Blake Martin on 11/30/2015.
 *
 */
public class StoryFragmentOne extends Fragment {
    Button saveBtn;
    EditText storyTitle;
    RadioGroup ageGroup;
    RadioButton ageBtn;
    RadioGroup classiGroup;
    RadioButton classiBtn;
    FrameLayout listFrag;
    DBHandler appDB = DBHandler.getInstance(getContext());
    EditText summary;


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View storyview = inflater.inflate(R.layout.fragment_story_one,
                container, false);
        final View baseview = inflater.inflate(R.layout.activity_story_base,
                container, false);

        storyTitle = (EditText) storyview.findViewById(R.id.new_title_edit_text);
        saveBtn = (Button) storyview.findViewById(R.id.storySaveBtn);
        storyTitle = (EditText) storyview.findViewById(R.id.new_title_edit_text);
        ageGroup = (RadioGroup) storyview.findViewById(R.id.age_radio_group);
        classiGroup = (RadioGroup) storyview.findViewById(R.id.classification_radio_group);
        listFrag = (FrameLayout) baseview.findViewById(R.id.story_fragment_id);
        summary = (EditText) storyview.findViewById(R.id.synopsis_edit_text);

        final TextView[] genreIcons = {(TextView) storyview.findViewById(R.id.action_button),
                (TextView) storyview.findViewById(R.id.bio_button),
                (TextView) storyview.findViewById(R.id.comedy_button),
                (TextView) storyview.findViewById(R.id.drama_button),
                (TextView) storyview.findViewById(R.id.fantasy_button),
                (TextView) storyview.findViewById(R.id.horror_button),
                (TextView) storyview.findViewById(R.id.history_button),
                (TextView) storyview.findViewById(R.id.mystery_button),
                (TextView) storyview.findViewById(R.id.romance_button),
                (TextView) storyview.findViewById(R.id.scifi_button),
                (TextView) storyview.findViewById(R.id.thriller_button)};

        for (int i =0; i<11; i++){
            genreIcons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.action_button:
                            storyAttributes();
                            break;
                        case R.id.bio_button:
                            bioG();
                            break;
                        case R.id.comedy_button:
                            comedyG();
                            break;
                        case R.id.drama_button:
                            dramaG();
                            break;
                        case R.id.fantasy_button:
                            fantasyG();
                            break;
                        case R.id.horror_button:
                            horrorG();
                            break;
                        case R.id.history_button:
                            historyG();
                            break;
                        case R.id.mystery_button:
                            mysteryG();
                            break;
                        case R.id.romance_button:
                            romanceG();
                            break;
                        case R.id.scifi_button:
                            scifiG();
                            break;
                        case R.id.thriller_button:
                            thrillerG();
                            break;
                    }
                }
            });
        }

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
                                    String delTitle = storyTitle.getText().toString();
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

        ImageView closeEdit = (ImageView) storyview.findViewById(R.id.exit_button);
        closeEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);
            }
        });

        final String titleTxt = getArguments().getString("titleKey");
        storyTitle.setText(titleTxt);

        // titleTxt is only empty if it's a new story, after pressing the '+' button
        if(titleTxt.equalsIgnoreCase("")){
            saveBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    String[] genreTags = new String[11];
                    for (int i =0; i<11; i++){
                        genreTags[i] = genreIcons[i].getTag().toString();
                        Log.v("taggy", genreTags[i]);
                    }

                    String genreStr = Arrays.toString(genreTags);
                    strToArr(genreStr);

                    //error checking for empty categories
                    if(genreStr.equalsIgnoreCase("[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]") ||
                            storyTitle.getText().toString().equalsIgnoreCase("") ||
                            ageGroup.getCheckedRadioButtonId() == -1 ||
                            classiGroup.getCheckedRadioButtonId() == -1 ||
                            summary.getText().toString().equalsIgnoreCase(""))
                    {
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
                        // add a new story
                        String ageStr = getAge();
                        String classiStr = getClassi();
                        String newTitle = storyTitle.getText().toString();
                        String sumStr = summary.getText().toString();
                        appDB.addStory(new StoryClass(newTitle, genreStr, ageStr, classiStr, sumStr,""));
                        populateListView(listFrag);
                        getActivity().findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);
                        Log.v("taggy","genreStr saved: " + genreStr);
                    }
                }
            });
        }

        else{
            String ageId = getArguments().getString("ageKey");
            int ageInt=R.id.age_radioButton;

            if (ageId.equalsIgnoreCase("Children")){
                ageInt = R.id.age_radioButton;
            }
            else if(ageId.equalsIgnoreCase("Young Adult")){
                ageInt = R.id.age_radioButton2;
            }
            else if(ageId.equalsIgnoreCase("Adult")){
                ageInt = R.id.age_radioButton3;
            }

            String classiId = getArguments().getString("classiKey");
            int classiInt = R.id.classification_radioButton;

            if(classiId.equalsIgnoreCase("Fiction")){
                classiInt = R.id.classification_radioButton;
            }
            else if(classiId.equalsIgnoreCase("Non-Fiction")){
                classiInt = R.id.classification_radioButton2;
            }

            String[] genreTags;
            genreTags = strToArr(getArguments().getString("genreKey"));

            for (int i =0; i<11; i++){
                genreIcons[i].setTag(genreTags[i]);
            }

            ageGroup.check(ageInt);
            classiGroup.check(classiInt);
            actionGLoad(storyview);
            bioGLoad(storyview);
            comedyGLoad(storyview);
            dramaGLoad(storyview);
            fantasyGLoad(storyview);
            horrorGLoad(storyview);
            historyGLoad(storyview);
            mysteryGLoad(storyview);
            romanceGLoad(storyview);
            scifiGLoad(storyview);
            thrillerGLoad(storyview);

            summary.setText(getArguments().getString("sumKey"));

            saveBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    String[] genreTags = new String[11];
                    for (int i = 0; i < 11; i++) {
                        genreTags[i] = genreIcons[i].getTag().toString();
                    }

                    List<StoryClass> storyList = appDB.getAllStories();
                    for (StoryClass s : storyList) {
                        String storyT = s.getTitle();
                        if (titleTxt.equalsIgnoreCase(storyT)) {
                            String ageStr = getAge();
                            String classiStr = getClassi();
                            String genreStr = Arrays.toString(genreTags);
                            strToArr(genreStr);
                            s.setAge(ageStr);
                            s.setClassi(classiStr);
                            s.setGenre(genreStr);
                            String newTitle = storyTitle.getText().toString();
                            s.setTitle(newTitle);
                            s.setSummary(summary.getText().toString());

                            appDB.updateStory(s);
                        }
                    }
                    populateListView(listFrag);
                    baseview.findViewById(R.id.story_list_id).setVisibility(View.VISIBLE);
                    getActivity().findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);

                }
            });
        }

        return storyview;
    }

    public String getAge(){
        ageGroup = (RadioGroup) getView().findViewById(R.id.age_radio_group);
        int selectedAge = ageGroup.getCheckedRadioButtonId();
        ageBtn = (RadioButton) getView().findViewById(selectedAge);
        String ageStr = ageBtn.getText().toString();
        return ageStr;
    }

    public String getClassi(){
        classiGroup = (RadioGroup) getView().findViewById(R.id.classification_radio_group);
        int selectedClassi = classiGroup.getCheckedRadioButtonId();
        classiBtn = (RadioButton) getView().findViewById(selectedClassi);
        Log.v("taggy",Integer.toString(selectedClassi));
        String classiStr = classiBtn.getText().toString();
        return classiStr;
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

    public void storyAttributes()
    {
        if (Integer.parseInt(getView().findViewById(R.id.action_button).getTag().toString()) == 1)
        {
            getView().findViewById(R.id.action_button).setBackgroundResource(R.drawable.action_selected);
            getView().findViewById(R.id.action_button).setTag(2);
        }
        else
        {
            getView().findViewById(R.id.action_button).setBackgroundResource(R.drawable.action);
            getView().findViewById(R.id.action_button).setTag(1);
        }

    }

    public void bioG(){
        if (Integer.parseInt(getView().findViewById(R.id.bio_button).getTag().toString()) == 1)
        {
            getView().findViewById(R.id.bio_button).setBackgroundResource(R.drawable.bio_selected);
            getView().findViewById(R.id.bio_button).setTag(2);
        }
        else
        {
            getView().findViewById(R.id.bio_button).setBackgroundResource(R.drawable.bio);
            getView().findViewById(R.id.bio_button).setTag(1);
        }

    }

    public void comedyG(){
        if (Integer.parseInt(getView().findViewById(R.id.comedy_button).getTag().toString()) == 1)
        {
            getView().findViewById(R.id.comedy_button).setBackgroundResource(R.drawable.humor_selected);
            getView().findViewById(R.id.comedy_button).setTag(2);
        }
        else
        {
            getView().findViewById(R.id.comedy_button).setBackgroundResource(R.drawable.humor);
            getView().findViewById(R.id.comedy_button).setTag(1);
        }

    }

    public void dramaG(){
        if (Integer.parseInt(getView().findViewById(R.id.drama_button).getTag().toString()) == 1)
        {
            getView().findViewById(R.id.drama_button).setBackgroundResource(R.drawable.drama_selected);
            getView().findViewById(R.id.drama_button).setTag(2);
        }
        else
        {
            getView().findViewById(R.id.drama_button).setBackgroundResource(R.drawable.drama);
            getView().findViewById(R.id.drama_button).setTag(1);
        }

    }

    public void fantasyG(){
        if (Integer.parseInt(getView().findViewById(R.id.fantasy_button).getTag().toString()) == 1)
        {
            getView().findViewById(R.id.fantasy_button).setBackgroundResource(R.drawable.fantasy_selected);
            getView().findViewById(R.id.fantasy_button).setTag(2);
        }
        else
        {
            getView().findViewById(R.id.fantasy_button).setBackgroundResource(R.drawable.fantasy);
            getView().findViewById(R.id.fantasy_button).setTag(1);
        }

    }

    public void horrorG(){
        if (Integer.parseInt(getView().findViewById(R.id.horror_button).getTag().toString()) == 1)
        {
            getView().findViewById(R.id.horror_button).setBackgroundResource(R.drawable.horror_selected);
            getView().findViewById(R.id.horror_button).setTag(2);
        }
        else
        {
            getView().findViewById(R.id.horror_button).setBackgroundResource(R.drawable.horror);
            getView().findViewById(R.id.horror_button).setTag(1);
        }

    }

    public void historyG(){
        if (Integer.parseInt(getView().findViewById(R.id.history_button).getTag().toString()) == 1)
        {
            getView().findViewById(R.id.history_button).setBackgroundResource(R.drawable.history_selected);
            getView().findViewById(R.id.history_button).setTag(2);
        }
        else
        {
            getView().findViewById(R.id.history_button).setBackgroundResource(R.drawable.history);
            getView().findViewById(R.id.history_button).setTag(1);
        }

    }
    public void mysteryG(){
        if (Integer.parseInt(getView().findViewById(R.id.mystery_button).getTag().toString()) == 1)
        {
            getView().findViewById(R.id.mystery_button).setBackgroundResource(R.drawable.mystery_selected);
            getView().findViewById(R.id.mystery_button).setTag(2);
        }
        else
        {
            getView().findViewById(R.id.mystery_button).setBackgroundResource(R.drawable.mystery);
            getView().findViewById(R.id.mystery_button).setTag(1);
        }

    }
    public void romanceG(){
        if (Integer.parseInt(getView().findViewById(R.id.romance_button).getTag().toString()) == 1)
        {
            getView().findViewById(R.id.romance_button).setBackgroundResource(R.drawable.romance_selected);
            getView().findViewById(R.id.romance_button).setTag(2);
        }
        else
        {
            getView().findViewById(R.id.romance_button).setBackgroundResource(R.drawable.romance);
            getView().findViewById(R.id.romance_button).setTag(1);
        }

    }
    public void scifiG(){
        if (Integer.parseInt(getView().findViewById(R.id.scifi_button).getTag().toString()) == 1)
        {
            getView().findViewById(R.id.scifi_button).setBackgroundResource(R.drawable.scifi_selected);
            getView().findViewById(R.id.scifi_button).setTag(2);
        }
        else
        {
            getView().findViewById(R.id.scifi_button).setBackgroundResource(R.drawable.scifi);
            getView().findViewById(R.id.scifi_button).setTag(1);
        }

    }

    public void thrillerG(){
        if (Integer.parseInt(getView().findViewById(R.id.thriller_button).getTag().toString()) == 1)
        {
            getView().findViewById(R.id.thriller_button).setBackgroundResource(R.drawable.thriller_selected);
            getView().findViewById(R.id.thriller_button).setTag(2);
        }
        else
        {
            getView().findViewById(R.id.thriller_button).setBackgroundResource(R.drawable.thriller);
            getView().findViewById(R.id.thriller_button).setTag(1);
        }

    }

    public String[] strToArr(String toConvert){
        String[] result;
        toConvert = toConvert.substring(1, toConvert.indexOf("]"));
        result = toConvert.split(", ");
        return result;
    }

    public void actionGLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.action_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.action_button).setBackgroundResource(R.drawable.action);
        }
        else
        {
            v.findViewById(R.id.action_button).setBackgroundResource(R.drawable.action_selected);
            v.findViewById(R.id.action_button).setTag(2);
        }
    }

    public void bioGLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.bio_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.bio_button).setBackgroundResource(R.drawable.bio);
        }
        else
        {
            v.findViewById(R.id.bio_button).setBackgroundResource(R.drawable.bio_selected);
            v.findViewById(R.id.bio_button).setTag(2);
        }
    }

    public void comedyGLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.comedy_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.comedy_button).setBackgroundResource(R.drawable.humor);
        }
        else
        {
            v.findViewById(R.id.comedy_button).setBackgroundResource(R.drawable.humor_selected);
            v.findViewById(R.id.comedy_button).setTag(2);
        }
    }

    public void dramaGLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.drama_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.drama_button).setBackgroundResource(R.drawable.drama);
        }
        else
        {
            v.findViewById(R.id.drama_button).setBackgroundResource(R.drawable.drama_selected);
            v.findViewById(R.id.drama_button).setTag(2);
        }
    }

    public void fantasyGLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.fantasy_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.fantasy_button).setBackgroundResource(R.drawable.fantasy);
        }
        else
        {
            v.findViewById(R.id.fantasy_button).setBackgroundResource(R.drawable.fantasy_selected);
            v.findViewById(R.id.fantasy_button).setTag(2);
        }
    }

    public void horrorGLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.horror_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.horror_button).setBackgroundResource(R.drawable.horror);
        }
        else
        {
            v.findViewById(R.id.horror_button).setBackgroundResource(R.drawable.horror_selected);
            v.findViewById(R.id.horror_button).setTag(2);
        }
    }

    public void historyGLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.history_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.history_button).setBackgroundResource(R.drawable.history);
        }
        else
        {
            v.findViewById(R.id.history_button).setBackgroundResource(R.drawable.history_selected);
            v.findViewById(R.id.history_button).setTag(2);
        }
    }

    public void mysteryGLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.mystery_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.mystery_button).setBackgroundResource(R.drawable.mystery);
        }
        else
        {
            v.findViewById(R.id.mystery_button).setBackgroundResource(R.drawable.mystery_selected);
            v.findViewById(R.id.mystery_button).setTag(2);
        }
    }

    public void romanceGLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.romance_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.romance_button).setBackgroundResource(R.drawable.romance);
        }
        else
        {
            v.findViewById(R.id.romance_button).setBackgroundResource(R.drawable.romance_selected);
            v.findViewById(R.id.romance_button).setTag(2);
        }
    }

    public void scifiGLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.scifi_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.scifi_button).setBackgroundResource(R.drawable.scifi);
        }
        else
        {
            v.findViewById(R.id.scifi_button).setBackgroundResource(R.drawable.scifi_selected);
            v.findViewById(R.id.scifi_button).setTag(2);
        }
    }

    public void thrillerGLoad(View v)
    {
        if (Integer.parseInt(v.findViewById(R.id.thriller_button).getTag().toString()) == 1)
        {
            v.findViewById(R.id.thriller_button).setBackgroundResource(R.drawable.thriller);
        }
        else
        {
            v.findViewById(R.id.thriller_button).setBackgroundResource(R.drawable.thriller_selected);
            v.findViewById(R.id.thriller_button).setTag(2);
        }
    }
}
