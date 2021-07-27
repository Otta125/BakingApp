package com.pop.bakingapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pop.bakingapp.R;
import com.pop.bakingapp.Utilities.Constants;

public class VideoActivity extends AppCompatActivity {

    String Video_url, Description,Img_Url;
    private VideoFragmentInterface videoFragmentInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent i = getIntent();
        if (i.hasExtra(Constants.DESCRIPTION_ID)) {
            Video_url = i.getStringExtra(Constants.VIDEO_ID);
            Description = i.getStringExtra(Constants.DESCRIPTION_ID);
            Img_Url= i.getStringExtra(Constants.IMG_ID);
            SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
            editor.putString(Constants.IMG_ID, Img_Url);
            editor.apply();
            videoFragmentInterface.sendDataMethodvideo(Video_url, Description,Img_Url);
        }
    }

    public void setOnDataListenervideo(VideoFragmentInterface interfaces) {
        videoFragmentInterface = interfaces;
    }

    public interface VideoFragmentInterface {
        void sendDataMethodvideo(String video, String desc, String img_Url);
    }
}
