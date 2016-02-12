package com.dhara.playbasis.fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dhara.playbasis.MyApp;
import com.dhara.playbasis.R;
import com.dhara.playbasis.constants.Global;
import com.dhara.playbasis.models.Resources;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by dhara on 2/11/2016.
 */
public class GalleryDetailFragment extends Fragment {
    private static GalleryDetailFragment mFragment;
    private Resources mResource;
    private View mView;
    private ImageView mImgView;
    private ImageLoader mImageLoader;

    public static GalleryDetailFragment newInstance(Resources resource){
        mFragment = new GalleryDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Global.INTENT_RESOURCE, resource);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_detail, container, false);
        mImgView = (ImageView)mView.findViewById(R.id.imgLarge);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mImageLoader = ImageLoader.getInstance();
        mImageLoader.destroy();
        mImageLoader.init(ImageLoaderConfiguration.createDefault(MyApp.getAppContext()));

        if(getArguments() != null) {
            mResource = getArguments().getParcelable(Global.INTENT_RESOURCE);
            String url = MyApp.getAppContext().getCloudinary().url().fromIdentifier(mResource.getPublicId()).
                    generate(mResource.getPublicId()+"."+mResource.getFileExt());
            mImageLoader.displayImage(url, mImgView);
        }
    }
}
