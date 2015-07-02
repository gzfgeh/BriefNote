package com.gzfgeh.briefnote.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by guzhenf on 7/2/2015.
 */
public class ShowViewUtils {

    private ProgressDialog progressDialog;
    private Toast toast;
    private Context context;

    public ShowViewUtils(Context context){
        this.context = context;
    }

    public void showProgressDialog(String title,String message) {
        if(progressDialog==null) {

            progressDialog = ProgressDialog.show(context, title, message, true, false);
        }else if(progressDialog.isShowing()) {

            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if(progressDialog!=null&&progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void showToast(String hint){
        if (toast != null){
            toast=Toast.makeText(context,hint,Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void hideToast(){
        if (toast != null)
            toast.cancel();
    }
}
