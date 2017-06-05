package com.example.guest.tweetFollow.services;

import com.example.guest.tweetFollow.Constants;
import com.example.guest.tweetFollow.models.Tweet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class TwitterService {

    public static void findTweets(String location, Callback callback) {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(Constants.TWITTER_CONSUMER_KEY, Constants.TWITTER_CONSUMER_SECRET);
        consumer.setTokenWithSecret(Constants.TWITTER_TOKEN, Constants.TWITTER_TOKEN_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TWITTER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.TWITTER_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Tweet> processResults(Response response) {
        ArrayList<Tweet> tweets = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject twitterJSON = new JSONObject(jsonData);
                JSONArray tweetsJSON = twitterJSON.getJSONArray("tweets");
                for (int i = 0; i < tweetsJSON.length(); i++) {
                    JSONObject tweetJSON = tweetsJSON.getJSONObject(i);
                    String username = tweetJSON.getString("username");
                    String content = tweetJSON.getString("content");
                    //Date createdAt = createdAt.getTime();
                    String imageUrl = tweetJSON.getString("image_url");
//                    double latitude = tweetJSON.getJSONObject("location")
//                            .getJSONObject("coordinate").getDouble("latitude");
//                    double longitude = tweetJSON.getJSONObject("location")
//                            .getJSONObject("coordinate").getDouble("longitude");
//                    ArrayList<String> address = new ArrayList<>();
//                    JSONArray addressJSON = tweetJSON.getJSONObject("location")
//                            .getJSONArray("display_address");
//                    for (int y = 0; y < addressJSON.length(); y++) {
//                        address.add(addressJSON.get(y).toString());
//                    }

                    ArrayList<String> categories = new ArrayList<>();
                    JSONArray categoriesJSON = tweetJSON.getJSONArray("categories");

                    for (int y = 0; y < categoriesJSON.length(); y++) {
                        categories.add(categoriesJSON.getJSONArray(y).get(0).toString());
                    }
                    Tweet tweet = new Tweet(username, content, imageUrl);
                    tweets.add(tweet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweets;
    }
}