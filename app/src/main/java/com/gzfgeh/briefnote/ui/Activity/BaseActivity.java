package com.gzfgeh.briefnote.ui.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.WindowManager;

import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.model.ScopedBus;
import com.gzfgeh.briefnote.utils.SharePerferencesUtils;
import com.gzfgeh.briefnote.utils.ThemeUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by guzhenf on 6/25/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private final ScopedBus scopedBus = new ScopedBus();

    public ScopedBus getBus() {
        return scopedBus;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();    //must be before onCreate
        super.onCreate(savedInstanceState);
        initStatusWindow();
        setContentView(getContentView());
        initToolBar();
        getBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        scopedBus.paused();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scopedBus.resumed();
    }

    private void initTheme(){
        ThemeUtils.Theme theme = getCurrentTheme();
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

    private ThemeUtils.Theme getCurrentTheme(){
        int value = SharePerferencesUtils.getValue(this, ThemeUtils.CHANGE_THEME, 0);
        return ThemeUtils.Theme.mapValueToTheme(value);
    }

    /**
     * ToolBar back event
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected abstract void initToolBar();
    protected abstract int getContentView();
}
