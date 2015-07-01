package com.gzfgeh.briefnote.utils;

import android.app.Activity;

import com.gzfgeh.briefnote.R;

/**
 * Created by guzhenf on 6/24/2015.
 */
public class ThemeUtils {

    public static final String CHANGE_THEME = "CHANGE_THEME";

    public static void changTheme(Activity activity, Theme theme){
        if (activity == null)
            return;
        int style = R.style.RedTheme;
        switch (theme){
            case BROWN:
                style = R.style.BrownTheme;
                break;
            case BLUE:
                style = R.style.BlueTheme;
                break;
            case BLUE_GREY:
                style = R.style.BlueGreyTheme;
                break;
            case YELLOW:
                style = R.style.YellowTheme;
                break;
            case DEEP_PURPLE:
                style = R.style.DeepPurpleTheme;
                break;
            case PINK:
                style = R.style.PinkTheme;
                break;
            case GREEN:
                style = R.style.GreenTheme;
                break;
            default:
                break;
        }
        activity.setTheme(style);
    }

    public enum Theme{
        RED(0x00),
        BROWN(0x01),
        BLUE(0x02),
        BLUE_GREY(0x03),
        YELLOW(0x04),
        DEEP_PURPLE(0x05),
        PINK(0x06),
        GREEN(0x07);

        private int mValue;

        Theme(int value){
            this.mValue = value;
        }

        public static Theme mapValueToTheme(final int value) {
            for (Theme theme : Theme.values()) {
                if (value == theme.getIntValue()) {
                    return theme;
                }
            }
            // If run here, return default
            return RED;
        }

        static Theme getDefault()
        {
            return RED;
        }
        public int getIntValue() {
            return mValue;
        }
    }
}
