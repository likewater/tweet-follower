package com.example.guest.tweetFollow.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.guest.tweetFollow.R;
import com.example.guest.tweetFollow.models.Tweet;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TweetDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.tweetImageView) ImageView mImageLabel;
    @Bind(R.id.tweetNameTextView) TextView mNameLabel;
    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveTweetButton) TextView mSaveTweetButton;

    private Tweet mTweet;

    public static TweetDetailFragment newInstance(Tweet tweet) {
        TweetDetailFragment tweetDetailFragment = new TweetDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("tweet", Parcels.wrap(tweet));
        tweetDetailFragment.setArguments(args);
        return tweetDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTweet = Parcels.unwrap(getArguments().getParcelable("tweet"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweet_detail, container, false);
        ButterKnife.bind(this, view);

//        Picasso.with(view.getContext()).load(mTweet.getImageUrl()).into(mImageLabel);
        Picasso.with(view.getContext())
                .load(mTweet.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(mTweet.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mTweet.getCategories()));
        mRatingLabel.setText(Double.toString(mTweet.getRating()) + "/5");
        mPhoneLabel.setText(mTweet.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mTweet.getAddress()));

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mTweet.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mTweet.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mTweet.getLatitude()
                            + "," + mTweet.getLongitude()
                            + "?q=(" + mTweet.getName() + ")"));
            startActivity(mapIntent);
        }
    }
}
