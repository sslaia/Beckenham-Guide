package com.blogspot.sslaia.beckenhamtourguide;

public class GuideData {

    // Name of the subject/item/person/place/event
    private String mName;

    // The address of the place/person/event
    private String mPlace;

    // The description of the subject
    private String mDescription;

    // The category of the subject
    private String mCategory;

    // The photo of the subject
    private int mImageResource;

    // Create a new GuideData object
    // @param name is the name of the item/subject
    // @param place is the venue or place
    // @param description is additional information about the subject
    // @param category is one of the categories: restaurants, accommodation, events, etc.
    // @param imageResource is the correspondent image of the subject/item
    public GuideData(String name, String place, String description, String category, int imageResource) {
        mName = name;
        mPlace = place;
        mDescription = description;
        mCategory = category;
        mImageResource = imageResource;
    }

    public GuideData(String name, String place, int imageResource) {
        mName = name;
        mPlace = place;
        mImageResource = imageResource;
    }

    // Get the name of the subject
    public String getName() {
        return mName;
    }

    // Get the place/address of the subject
    public String getPlace() {
        return mPlace;
    }

    // Get the description of the place/subject
    public String getDescription() {
        return mDescription;
    }

    // Get the category the subject belongs to
    public String getCategory() {
        return mCategory;
    }

    // Get the photo the subject
    public int getImageResource() {
        return mImageResource;
    }

}
