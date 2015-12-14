package com.sunco.rolewriter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jit on 12/5/2015.
 *
 */
public class DBHandler extends SQLiteOpenHelper {

    private static DBHandler sInstance;

    public static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "AppDB";

    // Database table names
    private static final String STORIES_TABLE = "StoriesTable";
    private static final String CHAR_TABLE = "CharactersTable";

    // Stories Table Columns names
    private static final String COL_SID = "sid";
    private static final String COL_TITLE = "title";
    private static final String COL_GENRE = "genre";
    private static final String COL_AGERANGE = "agerange";
    private static final String COL_CLASSIFICATION = "classification";
    private static final String COL_SUMMARY = "summary";
    private static final String COL_SNOTES = "snotes";

    //Characters Table Column names
    private static final String COL_CID = "cid";
    private static final String COL_STORYNAME = "storyname";
    private static final String COL_NAME = "name";
    private static final String COL_DIR = "Direction";
    private static final String COL_GENDER = "gender";
    private static final String COL_AGE = "age";
    private static final String COL_LOC = "location";
    private static final String COL_OCC = "occupation";
    private static final String COL_INC = "income";
    private static final String COL_HEIGHT = "height";
    private static final String COL_WEIGHT = "weight";
    private static final String COL_EYEC = "eyec";
    private static final String COL_HAIRC = "hairc";
    private static final String COL_NATION = "nation";
    private static final String COL_HARDW = "hardwork";
    private static final String COL_HAPPY = "happy";
    private static final String COL_SMART = "smart";
    private static final String COL_POLITE = "polite";
    private static final String COL_SELFISH = "selfish";
    private static final String COL_QUIET = "quiet";
    private static final String COL_BRAVE = "brave";
    private static final String COL_CALM = "calm";
    private static final String COL_INTERESTS = "interests";
    private static final String COL_CNOTES = "cnotes";

    public static synchronized DBHandler getInstance(Context context){
        if (sInstance == null){
            sInstance = new DBHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    private DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STORIES_TABLE = "CREATE TABLE " + STORIES_TABLE + "("
                + COL_SID + " INTEGER PRIMARY KEY," + COL_TITLE + " TEXT,"
                + COL_GENRE + " TEXT,"
                + COL_AGERANGE + " TEXT,"
                + COL_CLASSIFICATION + " TEXT,"
                + COL_SUMMARY + " TEXT,"
                + COL_SNOTES + " TEXT"
                + ")";

        String CREATE_CHAR_TABLE = "CREATE TABLE " + CHAR_TABLE + "("
                + COL_CID + " INTEGER PRIMARY KEY," + COL_STORYNAME + " TEXT,"
                + COL_NAME + " TEXT,"
                + COL_DIR + " TEXT,"
                + COL_GENDER + " TEXT,"
                + COL_AGE + " TEXT,"
                + COL_LOC + " TEXT,"
                + COL_OCC + " TEXT,"
                + COL_INC + " TEXT,"
                + COL_HEIGHT + " TEXT,"
                + COL_WEIGHT + " TEXT,"
                + COL_EYEC + " TEXT,"
                + COL_HAIRC + " TEXT,"
                + COL_NATION + " TEXT,"
                + COL_HARDW + " TEXT,"
                + COL_HAPPY + " TEXT,"
                + COL_SMART + " TEXT,"
                + COL_POLITE + " TEXT,"
                + COL_SELFISH+ " TEXT,"
                + COL_QUIET+ " TEXT,"
                + COL_BRAVE+ " TEXT,"
                + COL_CALM+ " TEXT,"
                + COL_INTERESTS + " TEXT,"
                + COL_CNOTES + " TEXT"
                + ")";
        db.execSQL(CREATE_STORIES_TABLE);
        db.execSQL(CREATE_CHAR_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + STORIES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CHAR_TABLE);

        // Create tables again
        onCreate(db);
    }


    public void addStory(StoryClass story) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TITLE, story.getTitle());
        values.put(COL_GENRE, story.getGenre());
        values.put(COL_AGERANGE, story.getAge());
        values.put(COL_CLASSIFICATION, story.getClassi());
        values.put(COL_SUMMARY, story.getSummary());
        values.put(COL_SNOTES, story.getNotes());

        // Inserting Row
        db.insert(STORIES_TABLE, null, values);
        db.close(); // Closing database connection
    }

    public void addChar(CharacterClass characterClass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_STORYNAME, characterClass.getStoryName());
        values.put(COL_NAME, characterClass.getCharName());
        values.put(COL_DIR, characterClass.getDirection());
        values.put(COL_GENDER, characterClass.getGender());
        values.put(COL_AGE, characterClass.getAge());
        values.put(COL_LOC, characterClass.getLocation());
        values.put(COL_OCC, characterClass.getOccupation());
        values.put(COL_INC, characterClass.getIncome());
        values.put(COL_HEIGHT, characterClass.getHeight());
        values.put(COL_WEIGHT, characterClass.getWeight());
        values.put(COL_EYEC, characterClass.getEyeC());
        values.put(COL_HAIRC, characterClass.getHairC());
        values.put(COL_NATION, characterClass.getNation());
        values.put(COL_HARDW, characterClass.getHardwork());
        values.put(COL_HAPPY, characterClass.getHappy());
        values.put(COL_SMART, characterClass.getSmart());
        values.put(COL_POLITE, characterClass.getPolite());
        values.put(COL_SELFISH, characterClass.getSelfish());
        values.put(COL_QUIET, characterClass.getQuiet());
        values.put(COL_BRAVE, characterClass.getBrave());
        values.put(COL_CALM, characterClass.getCalm());
        values.put(COL_INTERESTS, characterClass.getInterests());
        values.put(COL_CNOTES, characterClass.getNotes());

        // Inserting Row
        db.insert(CHAR_TABLE, null, values);
        db.close(); // Closing database connection
    }

    public List<StoryClass> getAllStories() {
        List<StoryClass> storyList = new ArrayList<StoryClass>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + STORIES_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StoryClass story = new StoryClass();
                story.setID(Integer.parseInt(cursor.getString(0)));
                story.setTitle(cursor.getString(1));
                story.setGenre(cursor.getString(2));
                story.setAge(cursor.getString(3));
                story.setClassi(cursor.getString(4));
                story.setSummary(cursor.getString(5));
                story.setNotes(cursor.getString(6));
                // Adding joke to list
                storyList.add(story);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        // return joke list
        return storyList;
    }

    public List<CharacterClass> getAllChars(String s) {
        List<CharacterClass> characterList = new ArrayList<CharacterClass>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + CHAR_TABLE + " WHERE " + COL_STORYNAME + "='" + s + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CharacterClass newChar = new CharacterClass();
                newChar.setID(Integer.parseInt(cursor.getString(0)));
                newChar.setStoryName(cursor.getString(1));
                newChar.setCharName(cursor.getString(2));
                newChar.setDirection(cursor.getString(3));
                newChar.setGender(cursor.getString(4));
                newChar.setAge(cursor.getString(5));
                newChar.setLocation(cursor.getString(6));
                newChar.setOccupation(cursor.getString(7));
                newChar.setIncome(cursor.getString(8));
                newChar.setHeight(cursor.getString(9));
                newChar.setWeight(cursor.getString(10));
                newChar.setEyeC(cursor.getString(11));
                newChar.setHairC(cursor.getString(12));
                newChar.setNation(cursor.getString(13));
                newChar.setHardwork(cursor.getString(14));
                newChar.setHappy(cursor.getString(15));
                newChar.setSmart(cursor.getString(16));
                newChar.setPolite(cursor.getString(17));
                newChar.setSelfish(cursor.getString(18));
                newChar.setQuiet(cursor.getString(19));
                newChar.setBrave(cursor.getString(20));
                newChar.setCalm(cursor.getString(21));
                newChar.setInterests(cursor.getString(22));
                newChar.setNotes(cursor.getString(23));
                // Adding joke to list
                characterList.add(newChar);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        // return joke list
        return characterList;
    }

    public int getStoryCount(){
        String countQuery = "SELECT  * FROM " + STORIES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();
        //db.close();

        // return count
        return cursor.getCount();
    }

    public int getCharCount(String s){
        String countQuery = "SELECT  * FROM " + CHAR_TABLE + " WHERE " + COL_STORYNAME + "='" + s + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();
        //db.close();

        // return count
        return cursor.getCount();
    }

    public int updateStory(StoryClass story) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TITLE, story.getTitle());
        values.put(COL_GENRE, story.getGenre());
        values.put(COL_AGERANGE, story.getAge());
        values.put(COL_CLASSIFICATION, story.getClassi());
        values.put(COL_SUMMARY, story.getSummary());
        values.put(COL_SNOTES, story.getNotes());

        // updating row
        return db.update(STORIES_TABLE, values, COL_SID + " = ?",
                new String[]{String.valueOf(story.getID())});
    }

    public int updateChar(CharacterClass characterClass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_STORYNAME, characterClass.getStoryName());
        values.put(COL_NAME, characterClass.getCharName());
        values.put(COL_DIR, characterClass.getDirection());
        values.put(COL_GENDER, characterClass.getGender());
        values.put(COL_AGE, characterClass.getAge());
        values.put(COL_LOC, characterClass.getLocation());
        values.put(COL_OCC, characterClass.getOccupation());
        values.put(COL_INC, characterClass.getIncome());
        values.put(COL_HEIGHT, characterClass.getHeight());
        values.put(COL_WEIGHT, characterClass.getWeight());
        values.put(COL_EYEC, characterClass.getEyeC());
        values.put(COL_HAIRC, characterClass.getHairC());
        values.put(COL_NATION, characterClass.getNation());
        values.put(COL_HARDW, characterClass.getHardwork());
        values.put(COL_HAPPY, characterClass.getHappy());
        values.put(COL_SMART, characterClass.getSmart());
        values.put(COL_POLITE, characterClass.getPolite());
        values.put(COL_SELFISH, characterClass.getSelfish());
        values.put(COL_QUIET, characterClass.getQuiet());
        values.put(COL_BRAVE, characterClass.getBrave());
        values.put(COL_CALM, characterClass.getCalm());
        values.put(COL_INTERESTS, characterClass.getInterests());
        values.put(COL_CNOTES, characterClass.getNotes());

        // updating row
        return db.update(CHAR_TABLE, values, COL_CID + " = ?",
                new String[]{String.valueOf(characterClass.getID())});
    }

    public void deleteStory(StoryClass story) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STORIES_TABLE, COL_SID + " = ?",
                new String[]{String.valueOf(story.getID())});
        db.close();
    }

    public void deleteChar(CharacterClass characterClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CHAR_TABLE, COL_CID + " = ?",
                new String[]{String.valueOf(characterClass.getID())});
        db.close();
    }


}
