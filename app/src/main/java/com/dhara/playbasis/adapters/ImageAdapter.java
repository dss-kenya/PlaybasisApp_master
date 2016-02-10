package com.dhara.playbasis.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.dhara.playbasis.MyApp;
import com.dhara.playbasis.R;
import com.dhara.playbasis.models.Resources;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by USER on 07-02-2016.
 */
public class ImageAdapter extends BaseAdapter {
    private List<Resources> mResourceList;
    private Cloudinary mCloudinary;
    private Transformation thumbnailTransformation = null;
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;
    private float imageDimen;

    public ImageAdapter(List<Resources> resourceList) {
        mResourceList = resourceList;
        mCloudinary = MyApp.getAppContext().getCloudinary();
        imageDimen = MyApp.getAppContext().getResources().getDimension(R.dimen.image_dimen);
        thumbnailTransformation = new Transformation().width((int)imageDimen).height((int)imageDimen).crop("fill");
        mImageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        mImageLoader.init(ImageLoaderConfiguration.createDefault(MyApp.getAppContext()));
    }

    private String getUrl(int index) {
        Resources resources = mResourceList.get(index);
        // Cloudinary: generate a URL reflecting the thumbnail transformation on the given identifier.
        String url = mCloudinary.url().fromIdentifier(resources.getPublicId()).transformation(thumbnailTransformation).generate(resources.getPublicId()+".jpg");
        return url;
    }

    @Override
    public int getCount() {
        return mResourceList.size();
    }

    @Override
    public Object getItem(int position) {
        return mResourceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder vh = null;
        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.individual_image_row, parent, false);
            vh = new ViewHolder();
            vh.imgView = (ImageView)view.findViewById(R.id.img);
            vh.txtPublicId = (TextView)view.findViewById(R.id.txtPublicId);
            view.setTag(vh);
        }else {
            vh = (ViewHolder)view.getTag();
        }

        vh.txtPublicId.setText(mResourceList.get(position).getPublicId());
        mImageLoader.displayImage(getUrl(position), vh.imgView);

        return view;
    }

    static class ViewHolder{
        ImageView imgView;
        TextView txtPublicId;
    }
}
