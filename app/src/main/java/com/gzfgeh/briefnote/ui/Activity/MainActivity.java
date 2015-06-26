package com.gzfgeh.briefnote.ui.Activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.listener.OnMenuItemClickListener;
import com.gzfgeh.briefnote.listener.OnMenuItemLongClickListener;
import com.gzfgeh.briefnote.model.MenuObject;
import com.gzfgeh.briefnote.model.MenuParams;
import com.gzfgeh.briefnote.ui.Fragment.ContextMenuDialogFragment;

import java.util.ArrayList;
import java.util.List;

import static com.gzfgeh.briefnote.R.id.fab;

public class MainActivity extends BaseActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ContextMenuDialogFragment menuDialogFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initDrawerLayout();
        initMenuFragment();
        initFloatButton();
    }

    @Override
    protected void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        super.initToolBar(toolbar);
    }

    private void initDrawerLayout(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                toolbar.setTitle(R.string.app_name);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                toolbar.setTitle(R.string.app_name);
            }
        };
        drawerToggle.syncState();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.setScrimColor(getResources().getColor(R.color.drawer_scrim_color));
    }

    private void initMenuFragment(){
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int)getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        menuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null)
                    menuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);

                break;

            case R.id.toolbar_search:
                Toast.makeText(MainActivity.this, "action_share", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<MenuObject> getMenuObjects(){
        List<MenuObject> menuObjects = new ArrayList<MenuObject>();

        MenuObject close = new MenuObject();
        close.setSrcResID(R.drawable.icn_close);
        close.setBgColor(getColorPrimary());
        menuObjects.add(close);

        MenuObject send = new MenuObject("Send Message");
        send.setSrcResID(R.drawable.icn_1);
        send.setBgColor(getColorPrimary());
        menuObjects.add(send);

        MenuObject like = new MenuObject("Like Profile");
        like.setSrcResID(R.drawable.icn_2);
        like.setBgColor(getColorPrimary());
        menuObjects.add(like);

        MenuObject add = new MenuObject("Add to friends");
        add.setSrcResID(R.drawable.icn_3);
        add.setBgColor(getColorPrimary());
        menuObjects.add(add);

        MenuObject addFva = new MenuObject("Add to favorities");
        addFva.setSrcResID(R.drawable.icn_4);
        addFva.setBgColor(getColorPrimary());
        menuObjects.add(addFva);

        MenuObject block = new MenuObject("Block user");
        block.setSrcResID(R.drawable.icn_5);
        block.setBgColor(getColorPrimary());
        menuObjects.add(block);

        return menuObjects;
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    private void initFloatButton(){
        final FloatingActionButton button = (FloatingActionButton) findViewById(fab);

        button.setOnClickListener(new View.OnClickListener() {
            FloatingActionButton textBtn, photoBtn, soundsBtn, movieBtn;
            ObjectAnimator anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10;
            boolean isClicked = true;

            @Override
            public void onClick(View v) {
                if (isClicked) {
                    textBtn = (FloatingActionButton) findViewById(R.id.fab_text);
                    textBtn.setVisibility(View.VISIBLE);
                    photoBtn = (FloatingActionButton) findViewById(R.id.fab_photo);
                    photoBtn.setVisibility(View.VISIBLE);
                    soundsBtn = (FloatingActionButton) findViewById(R.id.fab_sounds);
                    soundsBtn.setVisibility(View.VISIBLE);
                    movieBtn = (FloatingActionButton) findViewById(R.id.fab_movie);
                    movieBtn.setVisibility(View.VISIBLE);

                    anim1 = ObjectAnimator.ofFloat(textBtn, "alpha", 0f, 1f);
                    anim2 = ObjectAnimator.ofFloat(textBtn, "y", textBtn.getTop(), 800);

                    anim3 = ObjectAnimator.ofFloat(photoBtn, "alpha", 0f, 1f);
                    anim4 = ObjectAnimator.ofFloat(photoBtn, "y", photoBtn.getTop(), 600);
                    anim5 = ObjectAnimator.ofFloat(photoBtn, "x", photoBtn.getLeft(), 500);

                    anim6 = ObjectAnimator.ofFloat(soundsBtn, "alpha", 0f, 1f);
                    anim7 = ObjectAnimator.ofFloat(soundsBtn, "y", soundsBtn.getTop(), 500);
                    anim8 = ObjectAnimator.ofFloat(soundsBtn, "x", soundsBtn.getLeft(), 600);

                    anim9 = ObjectAnimator.ofFloat(movieBtn, "alpha", 0f, 1f);
                    anim10 = ObjectAnimator.ofFloat(movieBtn, "x", movieBtn.getLeft(), 800);

                    isClicked = false;
                }else{
                    anim1 = ObjectAnimator.ofFloat(textBtn, "alpha", 1f, 0f);
                    anim2 = ObjectAnimator.ofFloat(textBtn, "y", textBtn.getTop(), button.getTop());

                    anim3 = ObjectAnimator.ofFloat(photoBtn, "alpha", 1f, 0f);
                    anim4 = ObjectAnimator.ofFloat(photoBtn, "y", photoBtn.getTop(), button.getTop());
                    anim5 = ObjectAnimator.ofFloat(photoBtn, "x", photoBtn.getLeft(), button.getLeft());

                    anim6 = ObjectAnimator.ofFloat(soundsBtn, "alpha", 1f, 0f);
                    anim7 = ObjectAnimator.ofFloat(soundsBtn, "y", soundsBtn.getTop(), button.getTop());
                    anim8 = ObjectAnimator.ofFloat(soundsBtn, "x", soundsBtn.getLeft(), button.getLeft());

                    anim9 = ObjectAnimator.ofFloat(movieBtn, "alpha", 1f, 0f);
                    anim10 = ObjectAnimator.ofFloat(movieBtn, "x", movieBtn.getLeft(), button.getLeft());

                    textBtn = (FloatingActionButton) findViewById(R.id.fab_text);
                    textBtn.setVisibility(View.GONE);
                    photoBtn = (FloatingActionButton) findViewById(R.id.fab_photo);
                    photoBtn.setVisibility(View.GONE);
                    soundsBtn = (FloatingActionButton) findViewById(R.id.fab_sounds);
                    soundsBtn.setVisibility(View.GONE);
                    movieBtn = (FloatingActionButton) findViewById(R.id.fab_movie);
                    movieBtn.setVisibility(View.GONE);

                    isClicked = true;
                }

                AnimatorSet animSet = new AnimatorSet();
                animSet.setDuration(300);
                animSet.setInterpolator(new LinearInterpolator());
                animSet.play(anim1).with(anim2);
                animSet.play(anim2).with(anim3);
                animSet.play(anim3).with(anim4);
                animSet.play(anim4).with(anim5);
                animSet.play(anim5).with(anim6);
                animSet.play(anim6).with(anim7);
                animSet.play(anim7).with(anim8);
                animSet.play(anim8).with(anim9);
                animSet.play(anim9).with(anim10);
                animSet.start();
            }
        });

    }
}
