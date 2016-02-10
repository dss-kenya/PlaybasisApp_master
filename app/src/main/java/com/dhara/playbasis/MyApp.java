package com.dhara.playbasis;

import android.app.Application;
import android.content.Context;

import com.cloudinary.Cloudinary;
import com.dhara.playbasis.constants.Global;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by USER on 07-02-2016.
 */
public class MyApp extends Application {
    private static MyApp mApp;
    private static Context mContext;

    private Cloudinary mCloudinary;

    /**
     * @return An initialized Cloudinary instance
     */
    public Cloudinary getCloudinary() {
        return mCloudinary;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initCloudinary();
    }

    public static MyApp getAppContext(){
        if(mApp==null){
            mApp = (MyApp)mContext;
        }
        return mApp;
    }

    private void initCloudinary() {
        // Cloudinary: creating a cloudinary instance using meta-data from manifest
        Map config = new HashMap();
        config.put(Global.CLOUD_NAME, Global.CLOUDINARY_NAME);
        config.put(Global.NETWORK_API_KEY, Global.APP_KEY_CLOUDINARY);
        config.put(Global.NETWORK_API_SECRET,Global.APP_SECRET_CLOUDINARY);
        mCloudinary = new Cloudinary(config);
    }

}
