package com.dhara.playbasis.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dhara.playbasis.R;
import com.dhara.playbasis.constants.Global;
import com.dhara.playbasis.fragments.GalleryFragment;

import java.io.File;

/**
 * Created by USER on 07-02-2016.
 * http://res.cloudinary.com/dharashah/image/list/playbasis_image.json?api_key=693247891758112&api_secret=urnJNWU5EpS8uvuYw2jE4D95BQA
 * http://stackoverflow.com/questions/24431943/how-do-i-get-a-list-of-my-images-from-cloudinary-from-client-side-javascript
 * http://support.cloudinary.com/hc/en-us/articles/203189031-How-to-retrieve-a-list-of-all-resources-sharing-the-same-tag-
 * https://github.com/m0she/cloudinary-android-parse-sample/tree/master/src/com/cloudinary/photoalbum
 */
public class GalleryDetailActivity extends BaseActivity {
    private Toolbar mToolbar;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        initToolbar();
        initFragment();
    }

    private void initToolbar(){
        mToolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initFragment(){
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container, GalleryFragment.newInstance());
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
