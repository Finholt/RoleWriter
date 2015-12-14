package com.sunco.rolewriter;

/**
 * Created by Jit on 12/5/2015.
 *
 */
public class StoryClass {
    int _id;
    String _title;
    String _genre;
    String _ageRange;
    String _classification;
    String _summary;
    String _notes;

    public StoryClass(){}

    public StoryClass(int id, String title, String genre, String ageRange, String classification, String summary, String notes){
        this._id = id;
        this._title = title;
        this._genre = genre;
        this._ageRange = ageRange;
        this._classification = classification;
        this._summary = summary;
        this._notes = notes;
    }

    public StoryClass(String title, String genre, String ageRange, String classification, String summary, String notes){
        this._title = title;
        this._genre = genre;
        this._ageRange = ageRange;
        this._classification = classification;
        this._summary = summary;
        this._notes = notes;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int i){
        this._id = i;
    }

    public String getTitle(){
        return this._title;
    }

    public void setTitle(String s){
        this._title = s;
    }

    public String getGenre(){
        return  this._genre;
    }

    public void setGenre(String s){
        this._genre = s;
    }

    public String getAge(){
        return this._ageRange;
    }

    public void setAge(String a){
        this._ageRange = a;
    }

    public String getClassi(){
        return this._classification;
    }

    public void setClassi(String s){
        this._classification = s;
    }

    public String getSummary(){return this._summary;}

    public void setSummary(String s){
        this._summary = s;
    }

    public String getNotes(){return this._notes;}

    public void setNotes(String s){
        this._notes = s;
    }
}
