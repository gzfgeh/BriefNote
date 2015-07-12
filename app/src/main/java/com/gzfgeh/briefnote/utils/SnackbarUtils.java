package com.gzfgeh.briefnote.utils;

import android.app.Activity;
import android.graphics.Color;

import com.gzfgeh.briefnote.ui.Activity.BaseActivity;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

/**
 * Created by guzhenf on 7/11/2015.
 */
public class SnackbarUtils {

    public static void show(Activity activity, int message) {
        int color = Color.BLACK;
        if (activity instanceof BaseActivity){
            color = (((BaseActivity) activity)).getColorPrimary();
        }
        color = color & 0xddffffff;
        SnackbarManager.show(
                Snackbar.with(activity.getApplicationContext())
                        .color(color)
                        .duration((Snackbar.SnackbarDuration.LENGTH_SHORT.getDuration() / 2))
                        .text(message), activity);
    }
}
