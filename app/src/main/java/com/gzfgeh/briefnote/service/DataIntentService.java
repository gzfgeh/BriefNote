package com.gzfgeh.briefnote.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;

import com.gzfgeh.briefnote.APP;
import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.database.DBObject;
import com.gzfgeh.briefnote.listener.FindListenerImpl;
import com.gzfgeh.briefnote.model.Note;
import com.gzfgeh.briefnote.utils.JsonUtils;
import com.gzfgeh.briefnote.utils.KeyUtils;
import com.gzfgeh.briefnote.utils.SharePerferencesUtils;
import com.gzfgeh.briefnote.utils.SnackbarUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import de.greenrobot.event.EventBus;

public class DataIntentService extends IntentService {

    public DataIntentService() {
        super("NetDataIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null)
            return;

        final String account = ((APP) getApplication()).getEmail();
        String type = intent.getStringExtra(KeyUtils.ACTION_KEY);

        if (KeyUtils.GET_NOTE_DATA.equals(type)){
            List<Note> itemList = DataSupport.findAll(Note.class);
            EventBus.getDefault().postSticky(itemList);
        }

        if (KeyUtils.GET_NET_DATA.equals(type)){
            BmobQuery<DBObject> query = new BmobQuery<>();
            query.addWhereEqualTo("email", account);
            query.findObjects(APP.getContext(), new FindListenerImpl<DBObject>(){
                DBObject dbObject;
                @Override
                public void onSuccess(List<DBObject> dbObjects) {
                    List<Note> notes = DataSupport.findAll(Note.class);
                    if (dbObjects != null && dbObjects.size() >= 1){
                        dbObject = dbObjects.get(0);
                        long localVersion = SharePerferencesUtils.getValue(DataIntentService.this, ((APP) getApplication()).getEmail(), 0);
                        if (dbObject.getVersion() > localVersion){
                            for(String string : dbObject.getNoteList()){
                                Note note = JsonUtils.parseNote(string);
                                if (note == null)
                                    return;

                                note.save();
                                SharePerferencesUtils.putValue(DataIntentService.this, account, dbObject.getVersion());
                            }
                            List<Note> itemList = DataSupport.findAll(Note.class);
                            EventBus.getDefault().postSticky(itemList);
                            return;
                        }else{
                            dbObject.setVersion(++localVersion);
                        }
                    }else{
                        dbObject = new DBObject();
                        dbObject.setEmail(account);
                        dbObject.setVersion(1);
                    }
                }

                @Override
                public void onError(int i, String s) {
                    super.onError(i, s);
                    SnackbarUtils.show((Activity) APP.getContext(), R.string.no_internet);
                }
            });
        }

    }
}
