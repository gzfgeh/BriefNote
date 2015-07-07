package com.gzfgeh.briefnote.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import com.gzfgeh.briefnote.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by guzhenf on 7/7/2015.
 */
public class AccountUtils {
    private static final String EMAIL_TYPE = "com.android.email";

    private AccountUtils(){}

    public static void findValidAccount(Context context, AccountFinderListener listener){
        if (listener == null)
            return;
        String accountString = SharePerferencesUtils.getValue(context, context.getResources().getString(R.string.sync_account_key), null);
        if (!TextUtils.isEmpty(accountString)){
            listener.setHasAccountSave(true);
        }
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        Account[] accounts = AccountManager.get(context.getApplicationContext()).getAccounts();
        List<String> accountItems = new ArrayList<>();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches() && TextUtils.equals(EMAIL_TYPE, account.type)) {
                accountItems.add(account.name);
            }
        }
        if (accountItems.size() <= 0){
            if (listener.isHasAccountSave()){
                listener.onOne(accountString);
                return;
            }
            listener.onNone();
        }
        if (accountItems.size() == 1){
            if (listener.isHasAccountSave()){
                listener.onOne(accountString);
                return;
            }
            listener.onOne(accountItems.get(0));
        }

        if (accountItems.size() > 1){
            listener.onMore(accountItems);
        }
    }

    public static abstract class AccountFinderListener{
        private boolean hasAccountSave = false;
        protected abstract void onNone();
        protected abstract void onOne(String account);
        protected abstract void onMore(List<String> accountItems);

        public boolean isHasAccountSave() {
            return hasAccountSave;
        }

        public void setHasAccountSave(boolean hasAccountSave) {
            this.hasAccountSave = hasAccountSave;
        }
    }
}
