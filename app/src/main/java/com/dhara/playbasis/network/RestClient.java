package com.dhara.playbasis.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import com.dhara.playbasis.MyApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dhara on 2/8/2016.
 */
public class RestClient {
    public static String BASE_URL="http://res.cloudinary.com/dharashah/image/list/playbasis_image.json?";
    private static RestClient mRestClient;
    public static RestClient getInstance(){
        if(mRestClient == null) {
            mRestClient = new RestClient();
        }
        return mRestClient;
    }

    public String getImages(HashMap<String, String> params) {
        String response = performGet(params);
        return response;
    }

    private String performGet(HashMap<String, String> params) {
        HttpURLConnection connection = null;
        try {
            String paramValues = populateParams(params);
            BASE_URL += paramValues;
            URL url = new URL(BASE_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            String response = convertStreamToString(is);
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
            Toast.makeText(MyApp.getAppContext(),
                    e.getMessage() + "\n" + e.getCause(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MyApp.getAppContext(),
                    e.getMessage() + "\n" + e.getCause(), Toast.LENGTH_LONG).show();
        } finally {
            connection.disconnect();
        }
        return null;
    }

    private String populateParams(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append((line + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    /**
     * Checks the availability of internet
     * @return
     */
    public static boolean isNetworkAvailable() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm =
                (ConnectivityManager) MyApp.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = cm.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network network : networks) {
                networkInfo = cm.getNetworkInfo(network);

                if (networkInfo.getTypeName().equalsIgnoreCase("WIFI"))
                    if (networkInfo.isConnected())
                        haveConnectedWifi = true;
                if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (networkInfo.isConnected())
                        haveConnectedMobile = true;

                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        }else {
            //noinspection deprecation
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        if (anInfo.getTypeName().equalsIgnoreCase("WIFI"))
                            if (anInfo.isConnected())
                                haveConnectedWifi = true;
                        if (anInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                            if (anInfo.isConnected())
                                haveConnectedMobile = true;
                    }
                }
            }
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
