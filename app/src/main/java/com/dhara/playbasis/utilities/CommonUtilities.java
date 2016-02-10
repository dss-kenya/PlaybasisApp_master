package com.dhara.playbasis.utilities;

import com.dhara.playbasis.MyApp;

/**
 * Created by USER on 07-02-2016.
 */
public class CommonUtilities {
    public static int getScreenHeight(){
        return MyApp.getAppContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return MyApp.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getOriginalScreenWidth(){
        return ((int)((float)MyApp.getAppContext().getResources().getDisplayMetrics().widthPixels /
                MyApp.getAppContext().getResources().getDisplayMetrics().density));
    }

    public static int getOriginalScreenHeight(){
        return ((int)((float)MyApp.getAppContext().getResources().getDisplayMetrics().heightPixels /
                MyApp.getAppContext().getResources().getDisplayMetrics().density));
    }
}
