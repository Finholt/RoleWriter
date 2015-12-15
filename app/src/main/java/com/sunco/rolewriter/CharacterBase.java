package com.sunco.rolewriter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

        //Gets the story name passed from the previous activity
        Intent PassedValues = getIntent();
        StoryName = PassedValues.getStringExtra("StoryName");

        //Sets the "help" text to the story title for reference
        TextView storyTitle = (TextView) findViewById(R.id.newStory);
        storyTitle.setText(StoryName);

        //Hides the nonexistent character fragment.  Used for back button functionality.
        findViewById(R.id.character_fragment_id).setVisibility(View.INVISIBLE);

        //Creates the ListView by creating a ListFragment
        findViewById(R.id.character_list_id).setVisibility(View.VISIBLE);
        if (findViewById(R.id.character_list_id) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("charKey", "");
            bundle.putString("storyKey", StoryName);
            CharacterList characterListFragment = new CharacterList();
            characterListFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.character_list_id, characterListFragment).commit();
        }
    }

    public void onClick(View v)
    {
        switch (v.getId()) {
            //Creates a new empty character fragment to add a new character
            case R.id.AddButton:
                findViewById(R.id.character_fragment_id).setVisibility(View.VISIBLE);
                Bundle bundle = new Bundle();
                bundle.putString("charKey","");
                bundle.putString("storyKey",StoryName);
                CharFragmentOne charFragmentOne = new CharFragmentOne();
                charFragmentOne.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.character_fragment_id, charFragmentOne).commit();
                break;
        }
    }

    //Passes character information into the fragment to edit an existing character
    public void EditCharacter(View view) {
        RelativeLayout relLay = (RelativeLayout) view.getParent();
        TextView textView = (TextView) relLay.findViewById(R.id.character_name);

        if (findViewById(R.id.character_fragment_id).getVisibility() == View.INVISIBLE) {
            findViewById(R.id.character_fragment_id).setVisibility(View.VISIBLE);
        }

        String dir = "";
        String gender = "";
        String age ="";
        String loc = "";
        String occ = "";
        String inc = "";
        String height = "";
        String weight = "";
        String eyec = "";
        String hairc = "";
        String nation = "";

        List<CharacterClass> characterList = appDB.getAllChars(StoryName);
        for (CharacterClass c : characterList) {
            String characterN = c.getCharName();
            if (textView.getText().toString().equalsIgnoreCase(characterN)) {
                dir = c.getDirection();
                gender = c.getGender();
                age = c.getAge();
                loc = c.getLocation();
                occ = c.getOccupation();
                inc = c.getIncome();
                height = c.getHeight();
                weight = c.getWeight();
                eyec = c.getEyeC();
                hairc = c.getHairC();
                nation = c.getNation();

                appDB.updateChar(c);

            }
        }

        //Launches edit character fragment
        if (findViewById(R.id.character_fragment_id) != null) {
            findViewById(R.id.character_fragment_id).setVisibility(View.VISIBLE);
            Bundle bundle = new Bundle();
            CharFragmentOne oldFrag = new CharFragmentOne();
            oldFrag.setArguments(bundle);
            bundle.putString("storyKey", StoryName);
            bundle.putString("charKey",textView.getText().toString());
            bundle.putString("dirKey",dir);
            bundle.putString("genderKey",gender);
            bundle.putString("ageKey",age);
            bundle.putString("locKey",loc);
            bundle.putString("occKey",occ);
            bundle.putString("incKey",inc);
            bundle.putString("heightKey",height);
            bundle.putString("weightKey",weight);
            bundle.putString("eyeKey",eyec);
            bundle.putString("hairKey",hairc);
            bundle.putString("nationKey", nation);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.character_fragment_id, oldFrag).commit();
        }
    }

    public void DelCharacter(View view) {
        RelativeLayout relLay = (RelativeLayout) view.getParent();
        final TextView textView = (TextView) relLay.findViewById(R.id.character_name);

        //Manages dialog popup for deletion confirmation
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Deletes the character
                        List<CharacterClass> charList = appDB.getAllChars(StoryName);
                        for (CharacterClass c : charList) {
                            String charN = c.getCharName();
                            if (textView.getText().toString().equalsIgnoreCase(charN)) {
                                appDB.deleteChar(c);
                            }
                        }

                        //Refreshes the ListView
                        if (findViewById(R.id.character_list_id) != null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("charKey", "");
                            bundle.putString("storyKey", StoryName);
                            CharacterList characterListFragment = new CharacterList();
                            characterListFragment.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction().replace(R.id.character_list_id, characterListFragment).commit();
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
        builder.setMessage("Are you sure you want to delete this character?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void ShowCharNotes(View view) {
        RelativeLayout relLay = (RelativeLayout) view.getParent();
        TextView textView = (TextView) relLay.findViewById(R.id.character_name);

        if (findViewById(R.id.character_fragment_id).getVisibility() == View.INVISIBLE) {
            findViewById(R.id.character_fragment_id).setVisibility(View.VISIBLE);
        }

        //Loads character notes from database
        String notes = "";
        List<CharacterClass> charList = appDB.getAllChars(StoryName);
        for (CharacterClass c : charList) {
            String charN = c.getCharName();
            if (textView.getText().toString().equalsIgnoreCase(charN)) {
                notes = c.getNotes();
            }
        }

        //Opens a new character notes fragment with previously entered notes loaded in
        findViewById(R.id.character_fragment_id).setVisibility(View.VISIBLE);
        Bundle bundle = new Bundle();
        bundle.putString("charKey",textView.getText().toString());
        bundle.putString("storyKey",StoryName);
        bundle.putString("notesKey", notes);
        CharNotesFragment charNotesFragment = new CharNotesFragment();
        charNotesFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.character_fragment_id, charNotesFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Hides keyboard when touching away from EditText field
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    @Override
    public void onBackPressed() {
        //Handles the back button differently depending on whether or not the edit fragment is visible
        if(findViewById(R.id.character_fragment_id).getVisibility() == View.INVISIBLE)
        {
            finish();
        }
        else
        {
            findViewById(R.id.character_fragment_id).setVisibility(View.INVISIBLE);
        }
    }
}
