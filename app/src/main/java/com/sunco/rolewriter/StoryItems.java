package com.sunco.rolewriter;

//Not actually called from anywhere, but we kept it as a good thing to have were we to develop the code further
public class StoryItems {

    private String storyName;

    public StoryItems()
    {

    }

    public StoryItems(String s)
    {
        this.storyName = s;
    }

    public String getStoryName()
    {
        return storyName;
    }

    public void setStoryName(String s)
    {
        this.storyName = s;
    }
}
