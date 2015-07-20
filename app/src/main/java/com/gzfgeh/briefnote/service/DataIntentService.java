package com.gzfgeh.briefnote.service;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;

import com.gzfgeh.briefnote.APP;
import com.gzfgeh.briefnote.database.DBObject;
import com.gzfgeh.briefnote.listener.FindListenerImpl;
import com.gzfgeh.briefnote.model.Note;
import com.gzfgeh.briefnote.utils.JsonUtils;
import com.gzfgeh.briefnote.utils.KeyUtils;
import com.gzfgeh.briefnote.utils.SharePerferencesUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
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
                        long localVersion = SharePerferencesUtils.getValue(((APP) getApplication()).getEmail(), 0);
                        if (dbObject.getVersion() > localVersion){
                            for(String string : dbObject.getNoteList()){
                                Note note = JsonUtils.parseNote(string);
                                if (note == null)
                                    return;

                                note.save();
                                SharePerferencesUtils.putValue(account, dbObject.getVersion());
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
                    dbObject.clearNotes();
                    for(Note note : notes)
                        dbObject.addNote(note);

                    if (TextUtils.isEmpty(dbObject.getObjectId())){
                        dbObject.save(APP.getContext(), new SaveListener() {
                            @Override
                            public void onSuccess() {
                                SharePerferencesUtils.putValue(account, dbObject.getVersion());
                                EventBus.getDefault().postSticky(KeyUtils.UPDATE_NET_SUCCESS);
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                EventBus.getDefault().postSticky(KeyUtils.UPDATE_NET_FAIL);
                            }
                        });
                    }else {
                        dbObject.update(APP.getContext(), new UpdateListener() {
                            @Override
                            public void onSuccess() {
                                SharePerferencesUtils.putValue(account, dbObject.getVersion());
                                EventBus.getDefault().postSticky(KeyUtils.UPDATE_NET_SUCCESS);
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                EventBus.getDefault().postSticky(KeyUtils.UPDATE_NET_FAIL);
                            }
                        });
                    }
                }

                @Override
                public void onError(int i, String s) {
                    super.onError(i, s);
                    EventBus.getDefault().postSticky(KeyUtils.GET_NET_FAIL);
                }
            });
        }

    }
}
