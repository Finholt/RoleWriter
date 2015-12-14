package com.sunco.rolewriter;

/**
 * Created by Jit on 12/13/2015.
 *
 */
public class CharacterClass {
    int _id;
    String _storyname;
    String _name;
    String _direction;
    String _gender;
    String _age;
    String _location;
    String _occupation;
    String _income;
    String _height;
    String _weight;
    String _eyeC;
    String _hairC;
    String _nation;
    String _hardwork;
    String _happy;
    String _smart;
    String _polite;
    String _selfish;
    String _quiet;
    String _brave;
    String _calm;
    String _interests;

    public CharacterClass(){}

    public CharacterClass(int id, String storyname, String name, String direction, String gender, String age, String location,
                          String occupation, String income, String height, String weight, String eyeC, String hairC,
                          String nation, String hardwork, String happy, String smart, String polite, String selfish,
                          String quiet, String brave, String calm, String interests){
        this._id = id;
        this._storyname = storyname;
        this._name = name;
        this._direction = direction;
        this._gender = gender;
        this._age = age;
        this._location = location;
        this._occupation = occupation;
        this._income = income;
        this._height = height;
        this._weight = weight;
        this._eyeC = eyeC;
        this._hairC = hairC;
        this._nation = nation;
        this._hardwork = hardwork;
        this._happy = happy;
        this._smart = smart;
        this._polite = polite;
        this._selfish = selfish;
        this._quiet = quiet;
        this._brave = brave;
        this._calm = calm;
        this._interests = interests;
    }

    public CharacterClass(String storyname, String name, String direction, String gender, String age, String location,
                          String occupation, String income, String height, String weight, String eyeC, String hairC,
                          String nation, String hardwork, String happy, String smart, String polite, String selfish,
                          String quiet, String brave, String calm, String interests){
        this._storyname = storyname;
        this._name = name;
        this._direction = direction;
        this._gender = gender;
        this._age = age;
        this._location = location;
        this._occupation = occupation;
        this._income = income;
        this._height = height;
        this._weight = weight;
        this._eyeC = eyeC;
        this._hairC = hairC;
        this._nation = nation;
        this._hardwork = hardwork;
        this._happy = happy;
        this._smart = smart;
        this._polite = polite;
        this._selfish = selfish;
        this._quiet = quiet;
        this._brave = brave;
        this._calm = calm;
        this._interests = interests;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getStoryName() {
        return this._storyname;
    }

    public void setStoryName(String s){
        this._storyname = s;
    }

    public String getCharName() {
        return this._name;
    }

    public void setCharName(String s){
        this._name = s;
    }

    public String getDirection(){
        return this._direction;
    }

    public void setDirection(String s){
        this._direction = s;
    }

    public String getGender(){
        return this._gender;
    }

    public void setGender(String s){
        this._gender = s;
    }

    public String getAge(){
        return this._age;
    }

    public void setAge(String s){
        this._age = s;
    }

    public String getLocation(){
        return this._location;
    }

    public void setLocation(String s){
        this._location = s;
    }

    public String getOccupation(){
        return this._occupation;
    }

    public void setOccupation(String s){
        this._occupation = s;

    }public String getIncome(){
        return this._income;
    }

    public void setIncome(String s){
        this._income = s;

    }public String getHeight(){
        return this._height;
    }

    public void setHeight(String s){
        this._height =  s;

    }
    public String getWeight(){
        return this._weight;
    }

    public void setWeight(String s){
        this._weight = s;
    }

    public String getEyeC(){
        return this._eyeC;
    }

    public void setEyeC(String s){
        this._eyeC = s;

    }public String getHairC(){
        return this._hairC;
    }

    public void setHairC(String s){
        this._hairC = s;

    }public String getNation(){
        return this._nation;
    }

    public void setNation(String s){
        this._nation = s;

    }public String getHardwork(){
        return this._hardwork;
    }

    public void setHardwork(String s){
        this._hardwork = s;

    }public String getHappy(){
        return this._happy;
    }

    public void setHappy(String s){
        this._happy = s;

    }public String getSmart(){
        return this._smart;
    }

    public void setSmart(String s){
        this._smart = s;

    }public String getPolite(){
        return this._polite;
    }

    public void setPolite(String s){
        this._polite = s;

    }public String getSelfish(){
        return this._selfish;
    }

    public void setSelfish(String s){
        this._selfish = s;

    }public String getQuiet(){
        return this._quiet;
    }

    public void setQuiet(String s){
        this._quiet = s;

    }public String getBrave(){
        return this._brave;
    }

    public void setBrave(String s){
        this._brave = s;

    }public String getCalm(){
        return this._calm;
    }

    public void setCalm(String s) {
        this._calm = s;
    }

    public String getInterests(){
        return this._interests;
    }
    public void setInterests(String s){
        this._interests = s;
    }

}
