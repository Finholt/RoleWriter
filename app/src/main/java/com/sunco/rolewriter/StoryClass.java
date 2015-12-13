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

    public StoryClass(){}

    public StoryClass(int id, String title, String genre, String ageRange, String classification){
        this._id = id;
        this._title = title;
        this._genre = genre;
        this._ageRange = ageRange;
        this._classification = classification;
    }

    public StoryClass(String title, String genre, String ageRange, String classification){
        this._title = title;
        this._genre = genre;
        this._ageRange = ageRange;
        this._classification = classification;
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
}
