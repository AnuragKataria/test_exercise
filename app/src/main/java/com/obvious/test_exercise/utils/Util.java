package com.obvious.test_exercise.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

import androidx.appcompat.app.AlertDialog;

import com.obvious.test_exercise.MainActivity;

import java.io.IOException;
import java.io.InputStream;

public class Util {
    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    public static AlertDialog showAlert(Context context, String message, String btn1, String btn2, boolean isSecondButtonEnable) {
        if (isSecondButtonEnable) {
            //Exit Popup
            return new AlertDialog.Builder(context)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton(btn1, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ((MainActivity) context).finish();
                        }
                    })
                    .setNegativeButton(btn2, null)
                    .show();
        } else {
            // Internet not available popup
            return new AlertDialog.Builder(context)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton(btn1, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (Util.isInternetAvailable(context)) {
                                dialog.dismiss();
                            } else {
                                showAlert(context, message, btn1, btn2, isSecondButtonEnable);
                            }
                        }
                    })
                    .show();
        }
    }
}
