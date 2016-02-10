package com.dhara.playbasis.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.cloudinary.utils.ObjectUtils;
import com.dhara.playbasis.MyApp;
import com.dhara.playbasis.R;
import com.dhara.playbasis.adapters.ImageAdapter;
import com.dhara.playbasis.asynctasks.GetImagesAsyncTask;
import com.dhara.playbasis.asynctasks.UploadImageAsyncTask;
import com.dhara.playbasis.constants.Global;
import com.dhara.playbasis.interfaces.IResponseListener;
import com.dhara.playbasis.models.ResourceHolder;
import com.dhara.playbasis.models.Resources;

import org.cloudinary.json.JSONException;
import org.cloudinary.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 07-02-2016.
 */
public class GalleryFragment extends Fragment implements IResponseListener{
    private View mView;
    private static GalleryFragment mFragment;
    private GridView mGridGallery;
    private ImageAdapter mImageAdpater;
    private IResponseListener mListener;
    private List<Resources> mResourceList;
    private ProgressBar mProgressBar;

    public static GalleryFragment newInstance(){
        mFragment = new GalleryFragment();
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
        mView= inflater.inflate(R.layout.fragment_gallery, container, false);
        mGridGallery =(GridView)mView.findViewById(R.id.gridGallery);
        mProgressBar = (ProgressBar)mView.findViewById(R.id.progressBar);
        mListener = this;
        mResourceList = new ArrayList<>();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImageAdpater = new ImageAdapter(mResourceList);
        mGridGallery.setAdapter(mImageAdpater);
        mProgressBar.setVisibility(View.VISIBLE);
        new GetImagesAsyncTask(mListener).execute();
    }

    @Override
    public void onResponseRecevied(Object object) {
        if(object != null) {
            if(object instanceof ResourceHolder) {
                ResourceHolder holder = (ResourceHolder)object;
                mResourceList.addAll(holder.getResourceList());

                if(mImageAdpater != null){
                    mImageAdpater.notifyDataSetChanged();
                }
            }else if(object instanceof  Resources) {
                // add the newly captured image here
                mResourceList.add((Resources)object);
                if(mImageAdpater != null){
                    mImageAdpater.notifyDataSetChanged();
                }
            }
        }
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_gallery, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_take_image:
                openCamera();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File f = new File(Environment
                .getExternalStorageDirectory(), "temp.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        startActivityForResult(intent, Global.TAKE_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Global.TAKE_IMAGE) {
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                if(f != null) {
                    for (File temp : f.listFiles()) {
                        if (temp.getName().equals("temp.jpg")) {
                            f = temp;
                            break;
                        }
                    }
                }

                new UploadImageAsyncTask(f, mListener).execute();
            }
        }
    }
}
