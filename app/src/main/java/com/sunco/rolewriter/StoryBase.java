package com.sunco.rolewriter;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.app.Fragment;
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
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.List;

public class StoryBase extends AppCompatActivity {


    DBHandler appDB;
    String[] stories;
    Button saveBtn;
    EditText storyTitle;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_base);

        appDB = DBHandler.getInstance(this);

        Context context = this;
        SharedPreferences sharePref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultTimeEntry = "true";
        String firstTime = sharePref.getString(getString(R.string.first_time_key),defaultTimeEntry);

        if(firstTime.equalsIgnoreCase("true")){

            appDB.addStory(new StoryClass("Harry Potter","[2, 1, 2, 2, 2, 1, 1, 1, 2, 1, 1]","Young Adult","Fiction","MAGIC ALL DAY ERR DAY",""));
            appDB.addStory(new StoryClass("Wowl Witer: The Biwogwaphy", "[2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 2]", "Adult", "Non-Fiction","IS DIS THE REAL LAIF?",""));

            SharedPreferences.Editor editor = sharePref.edit();
            editor.putString(getString(R.string.first_time_key), "false");
            editor.commit();
        }

        findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);

        if (findViewById(R.id.story_fragment_id) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("titleKey", "");
            StoryFragmentOne storyFragment = new StoryFragmentOne();
            storyFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.story_fragment_id, storyFragment).commit();
        }

        findViewById(R.id.story_list_id).setVisibility(View.VISIBLE);

        if (findViewById(R.id.story_list_id) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("titleKey", "");
            StoryList storyListFragment = new StoryList();
            storyListFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.story_list_id, storyListFragment).commit();
        }
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.storyList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                if (findViewById(R.id.story_fragment_id).getVisibility() == View.INVISIBLE) {
                    findViewById(R.id.story_fragment_id).setVisibility(View.VISIBLE);
                }

                TextView textView = (TextView) viewClicked;

                String ageG = "2131492993";
                String classi = "2131492998";
                String genre = "";

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
                    StoryFragmentOne oldFrag = new StoryFragmentOne();
                    oldFrag.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.story_fragment_id, oldFrag).commit();
                }
            }
        });
    }

    public void onClick(View v)
    {   //add button makes story fragment appear to edit the details about the story
        switch (v.getId()) {
            case R.id.AddButton:

                if (findViewById(R.id.story_fragment_id) != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("titleKey","");
                    StoryFragmentOne newFrag = new StoryFragmentOne();
                    newFrag.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.story_fragment_id, newFrag).commit();
                }
                showView();
                break;
        }
    }
    // function to show fragment and toggle it to appear disappear
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
        getMenuInflater().inflate(R.menu.menu_story_base, menu);
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

    public void OpenCharacters(View view) {
        TextView text = (TextView) view;
        Intent intent = new Intent(this, CharacterBase.class);

        intent.putExtra("StoryName", text.getText());
        startActivity(intent);
    }

    public void EditStory(View view) {
        RelativeLayout relLay = (RelativeLayout) view.getParent();
        TextView textView = (TextView) relLay.findViewById(R.id.story_name);
        //Toast.makeText(getApplicationContext(), textView.getText().toString(),
        //        Toast.LENGTH_LONG).show();

        if (findViewById(R.id.story_fragment_id).getVisibility() == View.INVISIBLE) {
            findViewById(R.id.story_fragment_id).setVisibility(View.VISIBLE);
        }

        String ageG = "2131492993";
        String classi = "2131492998";
        String genre = "";
        String summary = "";

        List<StoryClass> storyList = appDB.getAllStories();
        for (StoryClass s : storyList) {
            String storyT = s.getTitle();
            if (textView.getText().toString().equalsIgnoreCase(storyT)) {
                ageG = s.getAge();
                classi = s.getClassi();
                genre = s.getGenre();
                summary = s.getSummary();
            }
        }

        if (findViewById(R.id.story_fragment_id) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("titleKey", textView.getText().toString());
            bundle.putString("ageKey", ageG);
            bundle.putString("classiKey", classi);
            bundle.putString("genreKey", genre);
            bundle.putString("sumKey",summary);
            StoryFragmentOne oldFrag = new StoryFragmentOne();
            oldFrag.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.story_fragment_id, oldFrag).commit();
        }
    }

    public void DelStory(View view) {
        RelativeLayout relLay = (RelativeLayout) view.getParent();
        final TextView textView = (TextView) relLay.findViewById(R.id.story_name);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        List<StoryClass> storyList = appDB.getAllStories();
                        for (StoryClass s : storyList) {
                            String storyT = s.getTitle();
                            String delTitle = textView.getText().toString();
                            if (delTitle.equalsIgnoreCase(storyT)) {
                                appDB.deleteStory(s);
                            }
                        }

                        if (findViewById(R.id.story_list_id) != null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("titleKey", "");
                            StoryList storyListFragment = new StoryList();
                            storyListFragment.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction().replace(R.id.story_list_id, storyListFragment).commit();
                        }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        // Confirmation prompt
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this story?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void ShowStoryNotes(View view) {
        RelativeLayout relLay = (RelativeLayout) view.getParent();
        final TextView textView = (TextView) relLay.findViewById(R.id.story_name);

        if (findViewById(R.id.story_fragment_id).getVisibility() == View.INVISIBLE) {
            findViewById(R.id.story_fragment_id).setVisibility(View.VISIBLE);
        }

        String notes = "";

        List<StoryClass> storyList = appDB.getAllStories();
        for (StoryClass s : storyList) {
            String storyT = s.getTitle();
            if (textView.getText().toString().equalsIgnoreCase(storyT)) {
                notes = s.getNotes();
            }
        }

        if (findViewById(R.id.story_fragment_id) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("titleKey", textView.getText().toString());
            bundle.putString("notesKey",notes);
            StoryNotesFragment storyNotesFragment = new StoryNotesFragment();
            storyNotesFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.story_fragment_id, storyNotesFragment).commit();
        }
    }
}
