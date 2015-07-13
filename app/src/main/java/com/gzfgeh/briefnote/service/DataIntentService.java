package com.gzfgeh.briefnote.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;

import com.gzfgeh.briefnote.APP;
import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.database.DBObject;
import com.gzfgeh.briefnote.listener.FindListenerImpl;
import com.gzfgeh.briefnote.model.Note;
import com.gzfgeh.briefnote.utils.KeyUtils;
import com.gzfgeh.briefnote.utils.AccountUtils;
import com.gzfgeh.briefnote.utils.SharePerferencesUtils;
import com.gzfgeh.briefnote.utils.SnackbarUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

import cn.bmob.v3.BmobQuery;

public class DataIntentService extends IntentService {
    private String email = null;

    public DataIntentService() {
        super("NetDataIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String action = intent.getStringExtra(KeyUtils.ACTION_KEY);

        if (KeyUtils.GET_NOTE_DATA.equals(action)){

        }

        if (KeyUtils.GET_NET_DATA.equals(action)){
            String account = SharePerferencesUtils.getValue(APP.getContext(), R.string.sync_account_key, null);
            if (TextUtils.isEmpty(account)){
                AccountUtils.findValidAccount(APP.getContext(), new AccountUtils.AccountFinderListener() {
                    @Override
                    protected void onNone() {
                        SnackbarUtils.show((Activity) APP.getContext(), R.string.no_account_tip);
                    }

                    @Override
                    protected void onOne(String account) {
                        email = account;
                        SharePerferencesUtils.putValue(APP.getContext(), R.string.sync_account_key, account);
                    }

                    @Override
                    protected void onMore(List<String> accountItems) {
                        SnackbarUtils.show((Activity) APP.getContext(), R.string.no_account_tip);
                    }
                });
            }
        }
        return START_REDELIVER_INTENT;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null || email == null)
            return;

        String action = intent.getStringExtra(KeyUtils.ACTION_KEY);

        if (KeyUtils.GET_NOTE_DATA.equals(action)){
            List<Note> itemList = DataSupport.findAll(Note.class);

        }

        BmobQuery<DBObject> query = new BmobQuery<>();
        query.addWhereEqualTo("email", email);
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
}
