package com.gzfgeh.briefnote.ui.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.database.DBObject;
import com.gzfgeh.briefnote.utils.SharePerferencesUtils;
import com.gzfgeh.briefnote.utils.ShowViewUtils;
import com.gzfgeh.briefnote.utils.TimeUtils;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.TimePickerDialog;

import java.sql.Date;
import java.text.SimpleDateFormat;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by guzhenf on 7/1/2015.
 */
public class TextActivity extends BaseActivity {

    private Toolbar toolbar;
    private MenuItem finishMenuItem, timeMenuItem;
    private MaterialEditText titleEditText, contentEditText;
    private final String[] s = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initEditText();
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
                showDateAndTime();
//                BmobQuery<DBObject> bmobQuery = new BmobQuery<DBObject>();
//                bmobQuery.getObject(this, "11897ab790", new GetListener<DBObject>() {
//                    @Override
//                    public void onSuccess(DBObject object) {
//                        ShowViewUtils.showToast(TextActivity.this, object.getCreatedAt());
//                    }
//
//                    @Override
//                    public void onFailure(int code, String msg) {
//                        ShowViewUtils.showToast(TextActivity.this, "fail");
//                    }
//                });
                return true;

            case R.id.finish:
                Toast.makeText(this, "finish", Toast.LENGTH_SHORT).show();
                String title    = titleEditText.getText().toString();
                String content  = contentEditText.getText().toString();

                if (!(TextUtils.isEmpty(title)) && !(TextUtils.isEmpty(content)))
                    saveTextNote(title, content);

                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveTextNote(String title, String content){
        hideKeyBoard(titleEditText);
        DBObject dbObject = new DBObject();
        dbObject.setEmail(SharePerferencesUtils.getValue(this, getString(R.string.sync_account_key), null));
        dbObject.setTitle(title);
        dbObject.setContent(content);
        dbObject.setUrl("");

        if (s[0] == null || s[1] == null)
            dbObject.setAlertTime(new Date(0));
        else{
            String time = s[0] + " " + s[1];
            dbObject.setAlertTime(new Date(TimeUtils.timeFormatToLong(time)));
        }


        dbObject.save(this, new SaveListener(){

            @Override
            public void onSuccess() {
                ShowViewUtils.showToast(TextActivity.this, "success");
            }

            @Override
            public void onFailure(int i, String s) {
                ShowViewUtils.showToast(TextActivity.this, s + " fail");
            }
        });
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

    private void showDateAndTime(){
        Dialog.Builder builder = null;
        DialogFragment fragment;

        builder = new TimePickerDialog.Builder(24, 30){
            @Override
            public void onPositiveActionClicked(DialogFragment fragment) {
                TimePickerDialog dialog = (TimePickerDialog)fragment.getDialog();
                s[1] = dialog.getFormattedTime(new SimpleDateFormat("HH:mm:ss"));
                super.onPositiveActionClicked(fragment);
            }

            @Override
            public void onNegativeActionClicked(DialogFragment fragment) {
                super.onNegativeActionClicked(fragment);
            }
        };
        builder.positiveAction("OK").negativeAction("CANCEL");
        fragment = DialogFragment.newInstance(builder);
        fragment.show(getSupportFragmentManager(), null);

        builder = new DatePickerDialog.Builder(){
            @Override
            public void onPositiveActionClicked(DialogFragment fragment) {
                DatePickerDialog dialog = (DatePickerDialog)fragment.getDialog();
                s[0] = dialog.getFormattedDate(new SimpleDateFormat("yyyy-MM-dd"));
                super.onPositiveActionClicked(fragment);
            }

            @Override
            public void onNegativeActionClicked(DialogFragment fragment) {
                super.onNegativeActionClicked(fragment);
            }
        };

        builder.positiveAction("OK").negativeAction("CANCEL");
        fragment = DialogFragment.newInstance(builder);
        fragment.show(getSupportFragmentManager(), null);
    }
}
