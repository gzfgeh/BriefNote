package com.gzfgeh.briefnote.model;

import com.gzfgeh.briefnote.database.DBObject;

/**
 * Created by guzhenf on 7/8/2015.
 */
public final class NoteToDBObject {

    public static DBObject convert(Note note){
        if (note == null)
            return null;

        DBObject object = new DBObject();
//        object.setEmail(SharePerferencesUtils.getValue(APP.getContext(), R.string.sync_account_key, null));
//        object.setTitle(note.getTitle());
//        object.setContent(note.getContent());
//        object.setUrl(note.getUrl());
//        object.setAlertTime(note.getAlertTime());

        return object;
    }
}
