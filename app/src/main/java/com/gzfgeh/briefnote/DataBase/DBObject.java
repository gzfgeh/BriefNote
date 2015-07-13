package com.gzfgeh.briefnote.database;

import com.gzfgeh.briefnote.model.Note;
import com.gzfgeh.briefnote.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by guzhenf on 7/2/2015.
 */
public class DBObject extends BmobObject {

    public DBObject() {
        this("DBObject");
    }

    public DBObject(String theClassName) {
        super(theClassName);
    }

    private String email;

    private List<String> noteList = new ArrayList<>();

    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<String> noteList) {
        this.noteList = noteList;
    }

    public void addNote(Note note) {
        noteList.add(JsonUtils.jsonNote(note));
    }

    public void clearNotes() {
        noteList.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String note : noteList){
            sb.append(note);
            sb.append("\n");
        }
        return sb.toString();
    }
}
