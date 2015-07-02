package com.gzfgeh.briefnote.ui.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.database.DBObject;
import com.gzfgeh.briefnote.view.ScrollerPicker;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by guzhenf on 7/1/2015.
 */
public class TextActivity extends BaseActivity {

    private Toolbar toolbar;
    private MenuItem finishMenuItem, timeMenuItem;
    private MaterialEditText titleEditText, contentEditText;
    private Date alertTime;
    private ScrollerPicker scrollerPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initEditText();
        scrollerPicker = (ScrollerPicker) findViewById(R.id.scroller_picker);
    }

    private void initEditText(){
        titleEditText = (MaterialEditText) findViewById(R.id.lable_edit_text);
        contentEditText = (MaterialEditText) findViewById(R.id.content_edit_text);
        titleEditText.addTextChangedListener(new SimpleTextWatcher());
        contentEditText.addTextChangedListener(new SimpleTextWatcher());
    }

    @Override
    protected void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        super.initToolBar(toolbar);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_text;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_text, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        timeMenuItem = menu.getItem(0);
        finishMenuItem = menu.getItem(1);
        timeMenuItem.setVisible(false);
        finishMenuItem.setVisible(false);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.time:
                Toast.makeText(this, "time", Toast.LENGTH_SHORT).show();
                //ScrollerPicker scrollerPicker = new ScrollerPicker(TextActivity.this, R.attr.NumberPicker);
                scrollerPicker.setVisibility(View.VISIBLE);
                ArrayList<String> data = new ArrayList<String>();
                for (int i = 0; i < 10; i++)
                {
                    data.add("0" + i);
                }
                scrollerPicker.setData(data);

                //DatePickerDialog(TextActivity.this, AlertDialog.THEME_HOLO_LIGHT);
                return true;

            case R.id.finish:
                Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show();
                String title    = titleEditText.getText().toString();
                String content  = contentEditText.getText().toString();

                if (!(TextUtils.isEmpty(title)) && !(TextUtils.isEmpty(content)))
                    saveTextNote(title, content);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveTextNote(String title, String content){
        DBObject dbObject = new DBObject();
        dbObject.setTitle(title);
        dbObject.setContent(content);
        dbObject.setUrl("");
        dbObject.setCreateDate(new Date(System.currentTimeMillis()));
        dbObject.setOperateDate(new Date(System.currentTimeMillis()));
        dbObject.setAlertTime(new Date(System.currentTimeMillis()));
    }

    private void hideKeyBoard(MaterialEditText editText){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    class SimpleTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (finishMenuItem == null || timeMenuItem == null)
                return;

            String title    = titleEditText.getText().toString();
            String content  = contentEditText.getText().toString();

            if (!(TextUtils.isEmpty(title)) && !(TextUtils.isEmpty(content))){
                finishMenuItem.setVisible(true);
                timeMenuItem.setVisible(true);
            }else{
                finishMenuItem.setVisible(false);
                timeMenuItem.setVisible(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
