package com.dhara.playbasis.activities;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.dhara.playbasis.MyApp;
import com.dhara.playbasis.R;
import com.dhara.playbasis.fragments.GalleryFragment;
import com.dhara.playbasis.fragments.MainFragment;
import com.dhara.playbasis.fragments.NavigationDrawerFragment;
import com.dhara.playbasis.fragments.URLFragment;
import com.dhara.playbasis.utilities.CommonUtilities;

public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerListener {
    private Fragment mFragment;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationDrawerFragment mNavigationFragment;
    private NavigationDrawerFragment.NavigationDrawerListener mDrawerListener;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mDrawerLayout.isDrawerOpen(mFrameLayout)) {
            mDrawerLayout.closeDrawer(mFrameLayout);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mFrameLayout =(FrameLayout)findViewById(R.id.left_drawer);
        mDrawerListener =  this;

        DrawerLayout.LayoutParams params =
                new DrawerLayout.LayoutParams((int)((float) CommonUtilities.getScreenWidth() * 0.75),
                        DrawerLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.LEFT;
        mFrameLayout.setLayoutParams(params);
        mFrameLayout.invalidate();

        setupToolbar();
        initViews();
        setupDrawer();
    }

    /**
     * Sets up the toolbar
     */
    private void setupToolbar() {
        if(mToolbar != null) {
            mToolbar.setTitle(MyApp.getAppContext().getString(R.string.app_name));
            mToolbar.setTitleTextColor(MyApp.getAppContext()
                    .getResources().getColor(android.R.color.white));
            setSupportActionBar(mToolbar);
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Loads the home fragment and the side menu fragment
     */
    private void initViews(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mNavigationFragment = NavigationDrawerFragment.newInstance();
        mNavigationFragment.setListener(mDrawerListener);
        ft.replace(R.id.left_drawer, mNavigationFragment);
        ft.commit();

        mFragment = MainFragment.newInstance();
        onNavigationDrawerItemClicked(getString(R.string.home));
    }

    @Override
    public void onNavigationDrawerItemClicked(String menuItem) {
        if(mDrawerToggle != null){
            if(mDrawerLayout.isDrawerOpen(mFrameLayout)) {
                mDrawerLayout.closeDrawer(mFrameLayout);
            }
        }

        if(menuItem.equals(getString(R.string.web_url))){
            //load url fragment
            mToolbar.setTitle(MyApp.getAppContext().getString(R.string.web_url));
            mFragment = URLFragment.newInstance();
        }else if(menuItem.equals(getString(R.string.gallery))){
            mToolbar.setTitle(MyApp.getAppContext().getString(R.string.gallery));
            mFragment = GalleryFragment.newInstance();
        }else if(menuItem.equals(getString(R.string.home))) {
            mToolbar.setTitle(MyApp.getAppContext().getString(R.string.app_name));
            mFragment = MainFragment.newInstance();
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container, mFragment);
        ft.commit();
    }

    /**
     * Sets up the navigation drawer, and associated listeners
     */
    private void setupDrawer() {
        mDrawerToggle  =  new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }
}
