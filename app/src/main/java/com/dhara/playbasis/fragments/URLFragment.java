package com.dhara.playbasis.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import com.dhara.playbasis.MyApp;
import com.dhara.playbasis.R;
import com.dhara.playbasis.activities.WebviewActivity;
import com.dhara.playbasis.constants.Global;

/**
 * Created by USER on 07-02-2016.
 */
public class URLFragment extends Fragment implements View.OnClickListener {
    private static URLFragment mFragment;
    private View mView;
    private TableRow mTblRowGithub;
    private TableRow mTblRowBitbucket;
    private TableRow mTblRowGoogle;

    public static URLFragment newInstance(){
        mFragment = new URLFragment();
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_urls,container, false);
        mTblRowBitbucket=(TableRow)mView.findViewById(R.id.tblRowBitbucket);
        mTblRowGithub=(TableRow)mView.findViewById(R.id.tblRowGithub);
        mTblRowGoogle=(TableRow)mView.findViewById(R.id.tblRowGogole);

        mTblRowBitbucket.setOnClickListener(this);
        mTblRowGithub.setOnClickListener(this);
        mTblRowGoogle.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onClick(View v) {
        String url= "";
        switch (v.getId()) {
            case R.id.tblRowBitbucket:
                url = getString(R.string.bitbucket_url);
                break;

            case R.id.tblRowGithub:
                url = getString(R.string.github_url);
                break;

            case R.id.tblRowGogole:
                url = getString(R.string.google_url);
                break;
        }

        Intent intent = new Intent(MyApp.getAppContext(),
                WebviewActivity.class);
        intent.putExtra(Global.INTENT_URL, url);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
