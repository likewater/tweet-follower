package com.example.guest.tweetFollow.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.guest.tweetFollow.R;
import com.example.guest.tweetFollow.adapters.TweetPagerAdapter;
import com.example.guest.tweetFollow.models.Tweet;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TweetDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private TweetPagerAdapter adapterViewPager;
    ArrayList<Tweet> mTweets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        ButterKnife.bind(this);

        mTweets = Parcels.unwrap(getIntent().getParcelableExtra("tweets"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new TweetPagerAdapter(getSupportFragmentManager(), mTweets);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
