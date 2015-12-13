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

    // Jokes table name
    private static final String STORIES_TABLE = "StoriesTable";
    private static final String CHAR_TABLE = "CharactersTable";

    // Jokes Table Columns names
    private static final String COL_SID = "sid";
    private static final String COL_TITLE = "title";
    private static final String COL_GENRE = "genre";
    private static final String COL_AGERANGE = "agerange";
    private static final String COL_CLASSIFICATION = "classification";

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
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + STORIES_TABLE + "("
                + COL_SID + " INTEGER PRIMARY KEY," + COL_TITLE + " TEXT,"
                + COL_GENRE + " TEXT,"
                + COL_AGERANGE + " TEXT,"
                + COL_CLASSIFICATION + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + STORIES_TABLE);

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

        // Inserting Row
        db.insert(STORIES_TABLE, null, values);
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
                // Adding joke to list
                storyList.add(story);
            } while (cursor.moveToNext());
        }

        cursor.close();
        //db.close();

        // return joke list
        return storyList;
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

    public int updateStory(StoryClass story) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TITLE, story.getTitle());
        values.put(COL_GENRE, story.getGenre());
        values.put(COL_AGERANGE, story.getAge());
        values.put(COL_CLASSIFICATION, story.getClassi());

        // updating row
        return db.update(STORIES_TABLE, values, COL_SID + " = ?",
                new String[]{String.valueOf(story.getID())});
    }

    public void deleteStory(StoryClass story) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STORIES_TABLE, COL_SID + " = ?",
                new String[]{String.valueOf(story.getID())});
        db.close();
    }


}
