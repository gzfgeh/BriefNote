package com.gzfgeh.briefnote.service;

import android.app.IntentService;
import android.content.Intent;

import com.gzfgeh.briefnote.model.Note;
import com.gzfgeh.briefnote.utils.KeyUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

import de.greenrobot.event.EventBus;

public class DataIntentService extends IntentService {

    public DataIntentService() {
        super("NetDataIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null)
            return;

        String action = intent.getStringExtra(KeyUtils.ACTION_KEY);

        if (KeyUtils.GET_NOTE_DATA.equals(action)){
            List<Note> itemList = DataSupport.findAll(Note.class);
            EventBus.getDefault().postSticky(itemList);
        }

//        BmobQuery<DBObject> query = new BmobQuery<>();
//        query.addWhereEqualTo("email", email);
//        query.findObjects(APP.getContext(), new FindListenerImpl<DBObject>(){
//
//            @Override
//            public void onSuccess(List<DBObject> dbObjects) {
//
//            }
//
//            @Override
//            public void onError(int i, String s) {
//                super.onError(i, s);
//                SnackbarUtils.show((Activity)APP.getContext(), R.string.no_internet);
//            }
//        });

    }
}
