package com.dhara.playbasis.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.dhara.playbasis.MyApp;
import com.dhara.playbasis.R;
import com.dhara.playbasis.adapters.MenuAdapter;
import com.dhara.playbasis.utilities.CommonUtilities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 05-07-2015.
 */
public class NavigationDrawerFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View mView;
    private ListView mLstMenu;
    private String[] mMenuItems;
    private MenuAdapter mMenuAdapter;
    private static NavigationDrawerFragment mFragment;

    private NavigationDrawerListener mListener;

    public interface NavigationDrawerListener{
        void onNavigationDrawerItemClicked(String menuItem);
    }

    public void setListener(NavigationDrawerListener listener ) {
        mListener = listener;
    }

    public static NavigationDrawerFragment newInstance() {
        mFragment = new NavigationDrawerFragment();
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mLstMenu = (ListView)mView.findViewById(R.id.left_drawer);

        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams((int)((float) CommonUtilities.getScreenWidth() * 0.75),
                        FrameLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.LEFT;
        mLstMenu.setLayoutParams(params);
        mLstMenu.setOnItemClickListener(this);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMenuItems = getResources().getStringArray(R.array.menu_items);
        mMenuAdapter = new MenuAdapter(MyApp.getAppContext(),
                R.layout.individual_row,
                mMenuItems);
        mLstMenu.setAdapter(mMenuAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String itemName = mMenuItems[position];
        if(mListener != null) {
            mListener.onNavigationDrawerItemClicked(itemName);
        }
    }
}
