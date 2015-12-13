package com.sunco.rolewriter;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    DBHandler appDB;
    String[] stories;
    Button saveBtn;
    EditText storyTitle;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDB = DBHandler.getInstance(this);

        Context context = this;
        SharedPreferences sharePref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultTimeEntry = "true";
        String firstTime = sharePref.getString(getString(R.string.first_time_key),defaultTimeEntry);

        if(firstTime.equalsIgnoreCase("true")){

            appDB.addStory(new StoryClass("Harry Potter","[2, 1, 2, 2, 2, 1, 1, 1, 2, 1, 1]","Young Adult","Fiction"));
            appDB.addStory(new StoryClass("Wowl Witer: The Biwogwaphy", "[2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 2]", "Adult", "Non-Fiction"));

            SharedPreferences.Editor editor = sharePref.edit();
            editor.putString(getString(R.string.first_time_key), "false");
            editor.commit();
        }

        findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);

        if (findViewById(R.id.story_fragment_id) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("titleKey","");
            StoryFragment storyFragment = new StoryFragment();
            storyFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.story_fragment_id, storyFragment).commit();
        }


        populateListView();
        registerClickCallback();
    }

    private void populateListView() {

        List<StoryClass> storyList = appDB.getAllStories();
        int storyCount = appDB.getStoryCount();
        //String[] stories = {"Wicked", "Cyborg Book", "Harry Potter"};
        stories = new String[storyCount];

        int i = 0;
        for (StoryClass s : storyList){
            stories[i] = s.getTitle();
            i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.story_items, stories);

        ListView list = (ListView) findViewById(R.id.storyList);
        list.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.storyList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                Intent intent = new Intent(getBaseContext(), CharacterActivity.class);
                startActivity(intent);

                /* Code for Edit Button
                if (findViewById(R.id.story_fragment_id).getVisibility() == View.INVISIBLE) {
                    findViewById(R.id.story_fragment_id).setVisibility(View.VISIBLE);
                }

                TextView textView = (TextView) viewClicked;

                String ageG = "2131492993";
                String classi = "2131492998";
                String genre ="";

                List<StoryClass> storyList = appDB.getAllStories();
                for (StoryClass s : storyList) {
                    String storyT = s.getTitle();
                    if (textView.getText().toString().equalsIgnoreCase(storyT)) {
                        ageG = s.getAge();
                        classi = s.getClassi();
                        genre = s.getGenre();
                    }
                }

                if (findViewById(R.id.story_fragment_id) != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("titleKey", textView.getText().toString());
                    bundle.putString("ageKey", ageG);
                    bundle.putString("classiKey", classi);
                    bundle.putString("genreKey", genre);
                    StoryFragment oldFrag = new StoryFragment();
                    oldFrag.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.story_fragment_id, oldFrag).commit();
                }*/

            }
        });
    }

    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.AddButton:

                if (findViewById(R.id.story_fragment_id) != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("titleKey","");
                    StoryFragment newFrag = new StoryFragment();
                    newFrag.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.story_fragment_id, newFrag).commit();
                }
                showView();
                break;
        }
    }

    public void showView()
    {
        if (findViewById(R.id.story_fragment_id).getVisibility() == View.INVISIBLE)
        {
            findViewById(R.id.story_fragment_id).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.story_fragment_id).setVisibility(View.VISIBLE);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void closeEditor(View view)
    {
        findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);
    }
}
