package com.example.guest.tweetFollow.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Date;

@Parcel
public class Tweet {
    String mUsername;
    String mContent;
    Date mCreatedAt;
    String mImageUrl;


//    String mName;
//    String mPhone;
//    String mWebsite;
//    double mRating;
//    String mImageUrl;
//    ArrayList<String> mAddress = new ArrayList<>();
//    double mLatitude;
//    double mLongitude;
//    ArrayList<String> mCategories = new ArrayList<>();

    public Tweet() {}

    public Tweet(String username, String content, String imageUrl) {
        this.mUsername = username;
        this.mContent = content;
        //this.mCreatedAt = createdAt;
        mImageUrl = getLargeImageUrl(imageUrl);
    }

    public String getName() {
        return mUsername;
    }

    public String getContent() {
        return mContent;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

//    public ArrayList<String> getAddress() {
//        return mAddress;
//    }
//
//    public double getLatitude() {
//        return mLatitude;
//    }
//
//    public double getLongitude() {
//        return mLongitude;
//    }
//
//    public ArrayList<String> getCategories() {
//        return mCategories;
//    }

    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 6).concat("o.jpg");
        return largeImageUrl;
    }
}