package com.gzfgeh.briefnote.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.gzfgeh.briefnote.R;

/**
 * Created by guzhenf on 7/9/2015.
 */
public class NotesItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView titleText;
    private final TextView contentText;
    private final TextView timeText;

    public NotesItemViewHolder(View itemView) {
        super(itemView);
        titleText = (TextView) itemView.findViewById(R.id.note_label_text);
        contentText = (TextView) itemView.findViewById(R.id.note_content_text);
        timeText = (TextView) itemView.findViewById(R.id.note_last_edit_text);
    }

    public void setLabelText(CharSequence text){
        setTextView(titleText, text);
    }

    public void setLabelText(int text){
        setTextView(titleText, text);
    }

    public void setContentText(CharSequence text){
        setTextView(contentText, text);
    }

    public void setContentText(int text){
        setTextView(contentText, text);
    }

    public void setTimeText(CharSequence text){
        setTextView(timeText, text);
    }

    public void setTimeText(int text){
        setTextView(timeText, text);
    }

    private void setTextView(TextView view, CharSequence text){
        if (view == null || TextUtils.isEmpty(text))
            return;
        view.setText(text);
    }

    private void setTextView(TextView view, int text){
        if (view == null || text <= 0)
            return;
        view.setText(text);
    }
}
