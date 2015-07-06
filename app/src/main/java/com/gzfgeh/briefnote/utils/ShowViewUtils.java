package com.gzfgeh.briefnote.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;

import java.text.SimpleDateFormat;

/**
 * Created by guzhenf on 7/2/2015.
 */
public class ShowViewUtils {

    private static ProgressDialog progressDialog;
    private static Toast toast;

    public static String showDatePicker(FragmentManager manager){
        final String[] date = new String[1];
        Dialog.Builder builder = new DatePickerDialog.Builder(){
            @Override
            public void onPositiveActionClicked(DialogFragment fragment) {
                DatePickerDialog dialog = (DatePickerDialog)fragment.getDialog();
                date[0] = dialog.getFormattedDate(SimpleDateFormat.getDateInstance());
                super.onPositiveActionClicked(fragment);
            }

            @Override
            public void onNegativeActionClicked(DialogFragment fragment) {
                super.onNegativeActionClicked(fragment);
            }
        };

        builder.positiveAction("OK").negativeAction("CANCEL");
        DialogFragment fragment = DialogFragment.newInstance(builder);
        fragment.show(manager, null);
        return date[0];
    }

    public static void showProgressDialog(Context context, String title,String message) {
        if(progressDialog==null) {

            progressDialog = ProgressDialog.show(context, title, message, true, false);
        }else if(progressDialog.isShowing()) {

            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }
        progressDialog.show();
    }

    public static void hideProgressDialog() {
        if(progressDialog!=null&&progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static void showToast(Context context, String hint){
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
