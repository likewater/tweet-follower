package com.example.guest.tweetFollow.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.tweetFollow.R;
import com.example.guest.tweetFollow.adapters.TweetListAdapter;
import com.example.guest.tweetFollow.models.Tweet;
import com.example.guest.tweetFollow.services.TwitterService;

import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TweetsActivity extends AppCompatActivity {
    public static final String TAG = TweetsActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private TweetListAdapter mAdapter;

    public ArrayList<Tweet> mTweets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getTweets(location);
    }

    private void getTweets(String location) {
        final TwitterService twitterService = new TwitterService();

        twitterService.findTweets(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mTweets = twitterService.processResults(response);

                TweetsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new TweetListAdapter(getApplicationContext(), mTweets);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(TweetsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}

//public class TweetsActivity extends AppCompatActivity {
//    public static final String TAG = TweetsActivity.class.getSimpleName();
//
//    @Bind(R.id.locationTextView) TextView mLocationTextView;
//    @Bind(R.id.listView) ListView mListView;
//
//    public ArrayList<Tweet> mTweets = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tweets);
//        ButterKnife.bind(this);
//
//        Intent intent = getIntent();
//        String location = intent.getStringExtra("location");
//
//        mLocationTextView.setText("Here are all the tweets near: " + location);
//
//        getTweets(location);
//    }
//
//    private void getTweets(String location) {
//        final TwitterService yelpService = new TwitterService();
//
//        yelpService.findTweets(location, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                mTweets = yelpService.processResults(response);
//
//                TweetsActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        String[] tweetNames = new String[mTweets.size()];
//                        for (int i = 0; i < tweetNames.length; i++) {
//                            tweetNames[i] = mTweets.get(i).getName();
//                        }
//
//                        ArrayAdapter adapter = new ArrayAdapter(TweetsActivity.this,
//                                android.R.layout.simple_list_item_1, tweetNames);
//                        mListView.setAdapter(adapter);
//
//                        for (Tweet tweet : mTweets) {
//                            Log.d(TAG, "Name: " + tweet.getName());
//                            Log.d(TAG, "Phone: " + tweet.getPhone());
//                            Log.d(TAG, "Website: " + tweet.getWebsite());
//                            Log.d(TAG, "Image url: " + tweet.getImageUrl());
//                            Log.d(TAG, "Rating: " + Double.toString(tweet.getRating()));
//                            Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", tweet.getAddress()));
//                            Log.d(TAG, "Categories: " + tweet.getCategories().toString());
//                        }
//                    }
//                });
//            }
//        });
//    }
//}







//public class TweetsActivity extends AppCompatActivity {
//    @Bind(R.id.locationTextView) TextView mLocationTextView;
//    @Bind(R.id.listView) ListView mListView;
//
//    public ArrayList<Tweet> mTweets = new ArrayList<>();
//
//    public static final String TAG = TweetsActivity.class.getSimpleName();
//
//    private String[] tweets = new String[] {"Sweet Hereafte", "Cricket", "Hawthorne Fish House", "Viking Soul Food",
//            "Red Square", "Horse Brass", "Dick's Kitchen", "Taco Bell", "Me Kha Noodle Bar",
//            "La Bonita Taqueria", "Smokehouse Tavern", "Pembiche", "Kay's Bar", "Gnarly Grey", "Slappy Cakes", "Mi Mero Mole" };
//
//    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fish Dishs", "Scandinavian", "Coffee",
//            "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar",
//            "Breakfast", "Mexican" };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tweets);
//        ButterKnife.bind(this);
//
//        mListView = (ListView) findViewById(R.id.listView);
//        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
//
//        MyTweetsArrayAdapter adapter = new MyTweetsArrayAdapter(this, android.R.layout.simple_list_item_1,
//               tweets, cuisines); //must match constructor!
//
////        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tweets);
//        mListView.setAdapter(adapter);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String tweet = ((TextView)view).getText().toString();
//                Toast.makeText(TweetsActivity.this, tweet, Toast.LENGTH_LONG).show();
//                Log.v("TAG", "In the onItemClickListener!");
//            }
//        });
//
//        Intent intent = getIntent();
//        String location = intent.getStringExtra("location");
//        mLocationTextView.setText("Here are all the tweets near: " + location);
//        Log.d("TAG", "In the onCreate method!");
//
//        getTweets(location);
//
//    }
//
//    private void getTweets(String location) {
//        final TwitterService yelpService = new TwitterService();
//        yelpService.findTweets(location, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    String jsonData = response.body().string();
//                    if (response.isSuccessful()) {
//                        Log.v(TAG, jsonData);
//                        mTweets = yelpService.processResults(response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        });
//    }
//
//}
