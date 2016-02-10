package com.dhara.playbasis.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhara.playbasis.R;

/**
 * Created by USER on 07-02-2016.
 */
public class MainFragment extends Fragment {
    private View mView;
    private TextView mTxtText;
    private static MainFragment mFragment;

    public static MainFragment newInstance(){
        mFragment = new MainFragment();
        return mFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.fragment_home, container, false);
        mTxtText = (TextView)mView.findViewById(R.id.txtText);
        return mView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_large_text ) {
            mTxtText.setText(getResources().getString(R.string.large_sample_text));
            return true;
        }

        if(id == R.id.action_small_text) {
            mTxtText.setText(getResources().getString(R.string.small_sample_text));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
