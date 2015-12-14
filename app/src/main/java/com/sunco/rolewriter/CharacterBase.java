package com.sunco.rolewriter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CharacterBase extends AppCompatActivity {

    String StoryName;
    DBHandler appDB = DBHandler.getInstance(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_base);

        Intent PassedValues = getIntent();
        StoryName = PassedValues.getStringExtra("StoryName");

        TextView storyTitle = (TextView) findViewById(R.id.storyTitle);
        storyTitle.setText(StoryName);

        /*findViewById(R.id.character_fragment_id).setVisibility(View.INVISIBLE);

        if (findViewById(R.id.character_fragment_id) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("titleKey","");
            bundle.putString("storyKey",StoryName);
            CharFragmentOne charFragmentOne = new CharFragmentOne();
            charFragmentOne.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.character_fragment_id, charFragmentOne).commit();
        }*/

        findViewById(R.id.character_list_id).setVisibility(View.VISIBLE);

        if (findViewById(R.id.character_list_id) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("titleKey", "");
            bundle.putString("storyKey", StoryName);
            CharacterList characterListFragment = new CharacterList();
            characterListFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.character_list_id, characterListFragment).commit();
        }

        //findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);
        //populateListView();
    }
    private void populateListView() {

        //List<StoryClass> storyList = appDB.getAllStories();
        //int storyCount = appDB.getStoryCount();
        //String[] characters = {"Harry", "Ron", "Hermione"};
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
                        .add(R.id.character_fragment_id, charFragmentOne).commit();
                break;
        }
    }

    public void EditCharacter(View view) {
        TextView textView = (TextView) view;

        if (findViewById(R.id.character_fragment_id).getVisibility() == View.INVISIBLE) {
            findViewById(R.id.character_fragment_id).setVisibility(View.VISIBLE);
        }
        //TODO: Fix just about everything below this comment.  Use EditStory Method in StoryBase as reference.  Most is based off of Jit's code.
        String ageG = "2131492993";
        String classi = "2131492998";
        String genre = "";

        List<CharacterClass> characterList = appDB.getAllChars(StoryName);
        for (CharacterClass c : characterList) {
            String characterN = c.getCharName();
            if (textView.getText().toString().equalsIgnoreCase(characterN)) {
                ageG = c.getAge();
                classi = c.getClassi();
                genre = c.getGenre();
            }
        }

        if (findViewById(R.id.character_fragment_id) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("titleKey", textView.getText().toString());
            bundle.putString("ageKey", ageG);
            bundle.putString("classiKey", classi);
            bundle.putString("genreKey", genre);
            CharFragmentOne oldFrag = new CharFragmentOne();
            oldFrag.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.character_fragment_id, oldFrag).commit();
        }
    }
}
