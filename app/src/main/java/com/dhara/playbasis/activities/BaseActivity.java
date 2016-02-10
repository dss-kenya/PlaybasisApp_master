package com.dhara.playbasis.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dhara.playbasis.utilities.errorhandling.CustomExceptionHandler;

/**
 * Created by USER on 07-02-2016.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(Thread.getDefaultUncaughtExceptionHandler() instanceof CustomExceptionHandler)) {
            Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler(this));
        }
    }
}
