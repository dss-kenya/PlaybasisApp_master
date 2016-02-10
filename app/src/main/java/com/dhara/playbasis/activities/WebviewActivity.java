package com.dhara.playbasis.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dhara.playbasis.R;
import com.dhara.playbasis.constants.Global;
import com.dhara.playbasis.fragments.WebviewFragment;

/**
 * Created by USER on 07-02-2016.
 */
public class WebviewActivity extends BaseActivity {
    private WebviewFragment mFragment;
    private Toolbar mToolbar;
    private String mUrl;

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

        if(getIntent().getExtras() !=null){
            mUrl = getIntent().getStringExtra(Global.INTENT_URL);
        }

        mFragment = WebviewFragment.newInstance(mUrl);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container, mFragment);
        ft.commit();
    }

    private void initToolbar(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
