package com.sunco.rolewriter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CharacterBase extends AppCompatActivity {

    String StoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_base);

        Intent PassedValues = getIntent();
        StoryName = PassedValues.getStringExtra("StoryName");
        /*Toast.makeText(getApplicationContext(), StoryName,
                Toast.LENGTH_LONG).show();*/

        TextView storyTitle = (TextView) findViewById(R.id.storyTitle);
        storyTitle.setText(StoryName);


        /*if (findViewById(R.id.story_fragment_id) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("titleKey","");
            bundle.putString("storyKey",StoryName);
            CharFragmentOne charFragmentOne = new CharFragmentOne();
            charFragmentOne.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.story_fragment_id, charFragmentOne).commit();
        }*/

        //findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);
        populateListView();
    }
    private void populateListView() {

        //List<StoryClass> storyList = appDB.getAllStories();
        //int storyCount = appDB.getStoryCount();
        String[] characters = {"Harry", "Ron", "Hermione"};
        //stories = new String[storyCount];

        /*int i = 0;
        for (StoryClass s : storyList){
            stories[i] = s.getTitle();
            i++;
        }*/

        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.story_items, characters);

        //ListView list = (ListView) findViewById(R.id.storyList);
        //list.setAdapter(adapter);
    }

    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.AddButton:
                Bundle bundle = new Bundle();
                bundle.putString("titleKey","");
                bundle.putString("storyKey",StoryName);
                CharFragmentOne charFragmentOne = new CharFragmentOne();
                charFragmentOne.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.story_fragment_id, charFragmentOne).commit();
                break;
        }
    }
}
