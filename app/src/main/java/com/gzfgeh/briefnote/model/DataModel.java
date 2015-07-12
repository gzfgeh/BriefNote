package com.gzfgeh.briefnote.model;

import android.app.Activity;
import android.text.TextUtils;

import com.gzfgeh.briefnote.APP;
import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.database.DBObject;
import com.gzfgeh.briefnote.listener.FindListenerImpl;
import com.gzfgeh.briefnote.utils.AccountUtils;
import com.gzfgeh.briefnote.utils.SharePerferencesUtils;
import com.gzfgeh.briefnote.utils.SnackbarUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

import cn.bmob.v3.BmobQuery;

/**
 * Created by guzhenf on 7/11/2015.
 */
public class DataModel {

    private DataModel(){ }

    public static List<Note> getItemData(){
        List<Note> itemList = DataSupport.findAll(Note.class);
        return itemList;
    }

    public static List<Note> getNetData(){
        String account = SharePerferencesUtils.getValue(APP.getContext(), R.string.sync_account_key, null);
        if (TextUtils.isEmpty(account)){
            AccountUtils.findValidAccount(APP.getContext(), new AccountUtils.AccountFinderListener() {
                @Override
                protected void onNone() {
                    SnackbarUtils.show((Activity)APP.getContext(), R.string.no_account_tip);
                }

                @Override
                protected void onOne(String account) {
                    SharePerferencesUtils.putValue(APP.getContext(), R.string.sync_account_key, account);
                    syncNotes(account);
                }

                @Override
                protected void onMore(List<String> accountItems) {
                    SnackbarUtils.show((Activity)APP.getContext(), R.string.no_account_tip);
                }
            });
        }else{
            syncNotes(account);
        }
        return null;
    }

    private static void syncNotes(final String account){

        new Thread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<DBObject> query = new BmobQuery<>();
                query.addWhereEqualTo("email", account);
                query.setLimit(5);
                query.findObjects(APP.getContext(), new FindListenerImpl<DBObject>(){

                    @Override
                    public void onSuccess(List<DBObject> dbObjects) {

                    }

                    @Override
                    public void onError(int i, String s) {
                        super.onError(i, s);
                        SnackbarUtils.show((Activity)APP.getContext(), R.string.no_internet);
                    }
                });
            }
        }).start();
    }
}
