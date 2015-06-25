package com.gzfgeh.briefnote.ui.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.WindowManager;

import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.utils.ThemeUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by guzhenf on 6/25/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();    //must be before onCreate
        super.onCreate(savedInstanceState);
        initStatusWindow();
        initToolBar();
    }

    private void initTheme(){
        ThemeUtils.Theme theme = ThemeUtils.Theme.mapValueToTheme(0);
        ThemeUtils.changTheme(this, theme);
    }

    private void initStatusWindow(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getColorPrimary());
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    protected void initToolBar(Toolbar toolbar) {
        if (toolbar == null)
            return;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setBackgroundColor(getColorPrimary());
        toolbar.setTitleTextColor(getResources().getColor(R.color.action_bar_title_color));
        toolbar.collapseActionView();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //add back icon
            getSupportActionBar().setDisplayShowTitleEnabled(true); //display title
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        }

    }
    public int getColorPrimary(){
        TypedValue typedValue = new  TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    protected abstract void initToolBar();
}
