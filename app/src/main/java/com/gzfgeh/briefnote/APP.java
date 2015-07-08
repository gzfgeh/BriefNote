package com.gzfgeh.briefnote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.gzfgeh.briefnote.utils.AccountUtils;
import com.gzfgeh.briefnote.utils.SharePerferencesUtils;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

import java.util.List;

import cn.bmob.v3.Bmob;

/**
 * Created by guzhenf on 7/2/2015.
 */
public class APP extends LitePalApplication {

    private SQLiteDatabase db;
    private String email;
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        Bmob.initialize(this, "ff52ea8fbec04dfc826eef0ea661ea66");

        if (db != null)
            db = Connector.getDatabase();

        String account = SharePerferencesUtils.getValue(getApplicationContext(), getString(R.string.sync_account_key), null);
        if (TextUtils.isEmpty(account)) {
            AccountUtils.findValidAccount(getApplicationContext(), new AccountUtils.AccountFinderListener() {
                @Override
                protected void onNone() {
                    email = null;
                }

                @Override
                protected void onOne(String account) {
                    email = account;
                    SharePerferencesUtils.putValue(getApplicationContext(), getString(R.string.sync_account_key), account);
                }

                @Override
                protected void onMore(List<String> accountItems) {
                }
            });
        }

    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
