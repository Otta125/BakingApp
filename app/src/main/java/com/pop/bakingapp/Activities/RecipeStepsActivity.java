package com.pop.bakingapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.pop.bakingapp.Adapters.StepsAdapter;
import com.pop.bakingapp.Fragments.VideoFragment;
import com.pop.bakingapp.R;
import com.pop.bakingapp.Utilities.Constants;

public class RecipeStepsActivity extends AppCompatActivity
        implements StepsAdapter.OnClickListener {

    int ID;
    boolean TabletMode;
    private FragmentInterface fragmentInterfaceListener;
    public static boolean IF_TABLET = false;
    TextView Title;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        context = this;
        Title = findViewById(R.id.toolbar_title);


        if (findViewById(R.id.video_fragment) != null) {
            TabletMode = true;

            FragmentManager fragmentManager = getSupportFragmentManager();
            VideoFragment videoFragment = new VideoFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.video_fragment, videoFragment)
                    .commit();
        } else {
            TabletMode = false;
        }

        Intent i = getIntent();
        if (i.hasExtra(Constants.RECIPE_ID)) {
            ID = i.getIntExtra(Constants.RECIPE_ID, 0);
            Title.setText(i.getStringExtra(Constants.RECIPE_NAME));
            fragmentInterfaceListener.sendDataMethod(ID);
        }
    }

    ///interface
    public void setOnDataListener(FragmentInterface interfaces) {
        fragmentInterfaceListener = interfaces;
    }

    public interface FragmentInterface {
        void sendDataMethod(int ID);
    }

    @Override
    public void OnStepSelected(String Video_url, String Description, String thumbnailURL) {
        if (!TabletMode) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("tablet", false);
            editor.apply();
            Intent intent = new Intent(this, VideoActivity.class);
            intent.putExtra(Constants.DESCRIPTION_ID, Description);
            intent.putExtra(Constants.VIDEO_ID, Video_url);
            intent.putExtra(Constants.IMG_ID, thumbnailURL);
            startActivity(intent);

        } else {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("tablet", true);
            editor.apply();
            VideoFragment videoFragment = new VideoFragment();
            Bundle bundle = new Bundle();
            if (!Video_url.equals("")&&Video_url!=null) {
                bundle.putString("video", Video_url);
            }
            if (!thumbnailURL.equals("")&&thumbnailURL!=null) {
                bundle.putString("img", thumbnailURL);
            }
            VideoFragment fragobj = new VideoFragment();
            fragobj.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.video_fragment, fragobj)
                    .commit();
        }
    }

    public static VideoFragment newInstance(String video) {
        VideoFragment f = new VideoFragment();
        Bundle args = new Bundle();
        args.putString("video", video);
        f.setArguments(args);
        return f;
    }
}
