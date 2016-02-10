package com.dhara.playbasis.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.cloudinary.utils.ObjectUtils;
import com.dhara.playbasis.MyApp;
import com.dhara.playbasis.constants.Global;
import com.dhara.playbasis.interfaces.IResponseListener;
import com.dhara.playbasis.models.Resources;

import org.cloudinary.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by dhara on 2/10/2016.
 */
public class UploadImageAsyncTask extends AsyncTask<Void,Void,Resources> {
    private File mFile;
    private IResponseListener mListener;

    public UploadImageAsyncTask(File file, IResponseListener listener) {
        mFile = file;
        mListener = listener;
    }

    @Override
    protected Resources doInBackground(Void... params) {
        try{
            Map configParams = ObjectUtils.asMap(Global.NETWORK_TAGS,Global.CLOUDINARY_TAGS);
            Map uploadResult = MyApp.getAppContext().getCloudinary().uploader().upload(mFile,
                    configParams);

            Resources resource = new Resources();
            resource.setFileExt((String)uploadResult.get("format"));
            resource.setPublicId((String)uploadResult.get("public_id"));
            resource.setVersion(Double.parseDouble((String)uploadResult.get("version")));

            return resource;
        }catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Resources resources) {
        super.onPostExecute(resources);
        if(mListener != null) mListener.onResponseRecevied(resources);
    }
}
