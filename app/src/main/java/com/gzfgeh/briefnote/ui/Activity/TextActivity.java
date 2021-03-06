package com.gzfgeh.briefnote.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.model.Note;
import com.gzfgeh.briefnote.utils.KeyUtils;
import com.gzfgeh.briefnote.utils.ShowViewUtils;
import com.gzfgeh.briefnote.utils.TimeUtils;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.TimePickerDialog;

import java.text.SimpleDateFormat;

/**
 * Created by guzhenf on 7/1/2015.
 */
public class TextActivity extends BaseActivity {

    private Toolbar toolbar;
    private MenuItem finishMenuItem, timeMenuItem;
    private MaterialEditText titleEditText, contentEditText;
    private final String[] s = new String[2];
    private int type;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent(getIntent());
    }

    public void onEventMainThread(Note note){
        this.note = note;
        initEditText();
        initToolBar();
    }

    private void parseIntent(Intent intent){
        if (intent == null || intent.getExtras() == null)
            return;

        type = intent.getExtras().getInt(KeyUtils.INTENT_KEY, 0);
    }

    private void initEditText(){
        titleEditText = (MaterialEditText) findViewById(R.id.lable_edit_text);
        contentEditText = (MaterialEditText) findViewById(R.id.content_edit_text);

        switch (type){
            case KeyUtils.NEW_TEXT:
                break;

            case KeyUtils.EDIT_TEXT:
                titleEditText.setText(note.getTitle());
                contentEditText.setText(note.getContent());
                break;

            case KeyUtils.LOOK_TEXT:
                titleEditText.setText(note.getTitle());
                contentEditText.setText(note.getContent());
                break;
        }
        titleEditText.addTextChangedListener(new SimpleTextWatcher());
        contentEditText.addTextChangedListener(new SimpleTextWatcher());
    }

    @Override
    protected void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        super.initToolBar(toolbar);
        switch (type){
            case KeyUtils.NEW_TEXT:
                toolbar.setTitle(getString(R.string.new_text));
                break;

            case KeyUtils.LOOK_TEXT:
                toolbar.setTitle(getString(R.string.look_text));
                break;
        }
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
                return true;

            case R.id.finish:
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
        note.setType(KeyUtils.TEXT_NOTE);
        note.setTitle(title);
        note.setContent(content);
        note.setUrl("");
        note.setLastOprTime(TimeUtils.getCurrentTimeInLong());

        if (s[0] == null || s[1] == null)
            note.setAlertTime(0);
        else{
            String time = s[0] + " " + s[1];
            note.setAlertTime(TimeUtils.timeFormatToLong(time));
        }

        if (note.save()) {
            ShowViewUtils.showToast(R.string.save_native_success);
            getBus().post(KeyUtils.UPDATE_TEXT);
        }
        else {
            ShowViewUtils.showToast(R.string.save_native_fail);
            getBus().post(KeyUtils.NO_UPDATE);
        }
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
