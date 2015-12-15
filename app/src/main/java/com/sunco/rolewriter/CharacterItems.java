package com.sunco.rolewriter;
//

//Not actually called from anywhere, but we kept it as a good thing to have were we to develop the code further
public class CharacterItems {

    private String characterName;

    public CharacterItems()
    {

    }

    public CharacterItems(String s)
    {
        this.characterName = s;
    }

    public String getCharacterName()
    {
        return characterName;
    }

    public void setCharacterName(String s)
    {
        this.characterName = s;
    }
}

