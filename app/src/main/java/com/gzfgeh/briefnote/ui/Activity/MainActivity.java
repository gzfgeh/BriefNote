package com.gzfgeh.briefnote.ui.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.listener.OnMenuItemClickListener;
import com.gzfgeh.briefnote.listener.OnMenuItemLongClickListener;
import com.gzfgeh.briefnote.model.MenuObject;
import com.gzfgeh.briefnote.model.MenuParams;
import com.gzfgeh.briefnote.ui.Fragment.ContextMenuDialogFragment;

import java.util.ArrayList;
import java.util.List;

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
}
