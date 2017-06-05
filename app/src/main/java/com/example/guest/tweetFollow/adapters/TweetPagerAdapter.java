package com.example.guest.tweetFollow.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.guest.tweetFollow.models.Tweet;
import com.example.guest.tweetFollow.ui.TweetDetailFragment;

import java.util.ArrayList;

public class TweetPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Tweet> mTweets;

    public TweetPagerAdapter(FragmentManager fm, ArrayList<Tweet> tweets) {
        super(fm);
        mTweets = tweets;
    }

    @Override
    public Fragment getItem(int position) {
        return TweetDetailFragment.newInstance(mTweets.get(position));
    }

    @Override
    public int getCount() {
        return mTweets.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTweets.get(position).getName();
    }
}
