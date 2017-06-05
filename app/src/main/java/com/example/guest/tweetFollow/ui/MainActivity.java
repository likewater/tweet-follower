package com.example.guest.tweetFollow.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Typeface;

//public class MainActivity extends AppCompatActivity {
//    private Button mFindTweetsButton;
//    private EditText mLocationEditText;
//    private TextView mAppNameTextView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mLocationEditText = (EditText) findViewById(R.id.locationEditText);
//        mFindTweetsButton = (Button) findViewById(R.id.findTweetsButton);
//        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
//        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
//        mAppNameTextView.setTypeface(ostrichFont);
//
//        mFindTweetsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String location = mLocationEditText.getText().toString();
//                Intent intent = new Intent(MainActivity.this, TweetsActivity.class);
//                intent.putExtra("location", location);
//                startActivity(intent);
//            }
//        });
//    }
//}

import com.example.guest.tweetFollow.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findTweetsButton) Button mFindTweetsButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

        mFindTweetsButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                if (v == mFindTweetsButton) {
                    String location = mLocationEditText.getText().toString();
                    Intent intent = new Intent(MainActivity.this, TweetsActivity.class);
                    intent.putExtra("location", location);
                    startActivity(intent);
                }
            }
    }

