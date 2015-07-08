package com.gzfgeh.briefnote.model;

import com.gzfgeh.briefnote.APP;
import com.gzfgeh.briefnote.R;
import com.gzfgeh.briefnote.database.DBObject;
import com.gzfgeh.briefnote.utils.SharePerferencesUtils;

/**
 * Created by guzhenf on 7/8/2015.
 */
public final class NoteToDBObject {

    public static DBObject convert(Note note){
        if (note == null)
            return null;

        DBObject object = new DBObject();
        object.setEmail(SharePerferencesUtils.getValue(APP.getContext(), APP.getContext().getString(R.string.sync_account_key), null));
        return object;
    }
}
