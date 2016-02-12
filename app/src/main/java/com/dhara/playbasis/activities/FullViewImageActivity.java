package com.dhara.playbasis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dhara.playbasis.R;
import com.dhara.playbasis.constants.Global;
import com.dhara.playbasis.fragments.GalleryDetailFragment;
import com.dhara.playbasis.models.Resources;

/**
 * Created by dhara on 2/11/2016.
 */
public class FullViewImageActivity extends BaseActivity {
    private Toolbar mToolbar;
	private Resources mResource;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
		
		if(getIntent().getExtras() != null) {
			Bundle args = getIntent().getExtras().getBundle(Global.INTENT_RESOURCE_BUNDLE);
			mResource = args.getParcelable(Global.INTENT_RESOURCE);
		}
		
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
        ft.replace(R.id.frame_container, GalleryDetailFragment.newInstance(mResource));
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
