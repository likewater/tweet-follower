package com.example.guest.tweetFollow.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.tweetFollow.R;
import com.example.guest.tweetFollow.models.Tweet;
import com.example.guest.tweetFollow.ui.TweetDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TweetListAdapter extends RecyclerView.Adapter<TweetListAdapter.TweetViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Tweet> mTweets = new ArrayList<>();
    private Context mContext;

    public TweetListAdapter(Context context, ArrayList<Tweet> tweets) {
        mContext = context;
        mTweets = tweets;
    }

    @Override
    public TweetListAdapter.TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_list_item, parent, false);
        TweetViewHolder viewHolder = new TweetViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TweetListAdapter.TweetViewHolder holder, int position) {
        holder.bindTweet(mTweets.get(position));
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public class TweetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.tweetImageView) ImageView mTweetImageView;
        @Bind(R.id.tweetNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public TweetViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, TweetDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("tweets", Parcels.wrap(mTweets));
            mContext.startActivity(intent);
        }

        public void bindTweet(Tweet tweet) {

//            Picasso.with(mContext).load(tweet.getImageUrl()).into(mTweetImageView);
            Picasso.with(mContext)
                    .load(tweet.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mTweetImageView);

            mNameTextView.setText(tweet.getName());
//            mCategoryTextView.setText(tweet.getCategories().get(0));
//            mRatingTextView.setText("Rating: " + tweet.getRating() + "/5");
        }
    }
}
