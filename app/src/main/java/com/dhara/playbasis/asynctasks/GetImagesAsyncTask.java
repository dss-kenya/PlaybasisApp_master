package com.dhara.playbasis.asynctasks;

import android.os.AsyncTask;

import com.dhara.playbasis.constants.Global;
import com.dhara.playbasis.interfaces.IResponseListener;
import com.dhara.playbasis.models.ResourceHolder;
import com.dhara.playbasis.network.RestClient;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by dhara on 2/8/2016.
 */
public class GetImagesAsyncTask extends AsyncTask<Void, Void, ResourceHolder> {
    private IResponseListener mListener;

    public GetImagesAsyncTask(IResponseListener listener){
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ResourceHolder doInBackground(Void... params) {
        HashMap<String, String> mapParams =  new HashMap<>();
        mapParams.put(Global.NETWORK_API_KEY, Global.APP_KEY_CLOUDINARY);
        mapParams.put(Global.NETWORK_API_SECRET, Global.APP_SECRET_CLOUDINARY);
        String response = RestClient.getInstance().getImages(mapParams);

        Gson gson = new Gson();
        ResourceHolder holder = gson.fromJson(response, ResourceHolder.class);
        return holder;
    }

    @Override
    protected void onPostExecute(ResourceHolder resourceHolder) {
        super.onPostExecute(resourceHolder);
        if(mListener != null) mListener.onResponseRecevied(resourceHolder);
    }
}
