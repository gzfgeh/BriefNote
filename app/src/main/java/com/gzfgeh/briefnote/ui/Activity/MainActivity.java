package com.gzfgeh.briefnote.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.adapter.BaseRecyclerViewAdapter;
import com.gzfgeh.briefnote.adapter.NotesAdapter;
import com.gzfgeh.briefnote.listener.OnMenuItemClickListener;
import com.gzfgeh.briefnote.listener.OnMenuItemLongClickListener;
import com.gzfgeh.briefnote.model.DataModel;
import com.gzfgeh.briefnote.model.Note;
import com.gzfgeh.briefnote.service.DataIntentService;
import com.gzfgeh.briefnote.ui.Activity.HandleComponent.HandleFLoatButton;
import com.gzfgeh.briefnote.ui.Activity.HandleComponent.HandleMenu;
import com.gzfgeh.briefnote.ui.Fragment.ContextMenuDialogFragment;
import com.gzfgeh.briefnote.utils.KeyUtils;
import com.gzfgeh.briefnote.view.SwipeRefreshLayout;

import java.util.List;

import static com.gzfgeh.briefnote.R.id.fab;

public class MainActivity extends BaseActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ContextMenuDialogFragment menuDialogFragment;
    private FragmentManager fragmentManager;
    private FloatingActionButton button, textBtn, photoBtn, soundsBtn, movieBtn;
    private NotesAdapter recyclerAdapter;
    private boolean hasUpdateNote = false;
    private boolean isRefreshing = false;
    private List<Note> datas;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas = DataModel.getItemData();
        initDrawerLayout();
        initMenuFragment();
        initFloatButton();
        initRecyclerView();
        initSwipeRefreshView();
    }

    private void initSwipeRefreshView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        swipeRefreshLayout.setColor(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setLoadNoFull(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        swipeRefreshLayout.setOnLoadListener(new SwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setLoading(false);
                    }
                }, 3000);
            }
        });
    }


    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new NotesAdapter(datas, this);
        recyclerAdapter.setOnInViewClickListener(R.id.notes_item_root,
               new BaseRecyclerViewAdapter.onInternalClickListenerImpl<Note>(){
                   @Override
                   public void OnClickListener(View parentV, View v, Integer position, Note values) {
                       super.OnClickListener(parentV, v, position, values);
                       startNoteActivity(KeyUtils.EDIT_TEXT, values);
                   }
               });
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    protected void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        super.initToolBar(toolbar);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    public void onEventMainThread(Integer event) {
        switch (event){
            case KeyUtils.UPDATE_TEXT:
                hasUpdateNote = true;
                updateDisplay();
                break;

            case KeyUtils.NO_UPDATE:
                hasUpdateNote = false;
                break;
        }
    }

    public void onEventMainThread(List<Note> lists){
        datas = lists;
        recyclerAdapter.setList(datas);
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
        fragmentManager = getSupportFragmentManager();
        menuDialogFragment = HandleMenu.menuButtonClick(MainActivity.this);
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



    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    private void initFloatButton(){
        button = (FloatingActionButton) findViewById(fab);
        textBtn = (FloatingActionButton) findViewById(R.id.fab_text);
        photoBtn = (FloatingActionButton) findViewById(R.id.fab_photo);
        soundsBtn = (FloatingActionButton) findViewById(R.id.fab_sounds);
        movieBtn = (FloatingActionButton) findViewById(R.id.fab_movie);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandleFLoatButton.FloatButtonClick(button, textBtn, photoBtn, soundsBtn, movieBtn);
            }
        });

        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "photo", Toast.LENGTH_SHORT).show();
            }
        });

        soundsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "sounds", Toast.LENGTH_SHORT).show();
            }
        });

        movieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "movie", Toast.LENGTH_SHORT).show();
            }
        });

        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note();
                startNoteActivity(KeyUtils.NEW_TEXT, note);
            }
        });
    }

    private void startNoteActivity(int type, Note note){
        Intent intent = new Intent(this, TextActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(KeyUtils.INTENT_KEY, type);
        getBus().post(note);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void updateDisplay(){
        Intent intent = new Intent(this, DataIntentService.class);
        intent.putExtra(KeyUtils.ACTION_KEY, KeyUtils.GET_NOTE_DATA);
        startService(intent);
    }
}
