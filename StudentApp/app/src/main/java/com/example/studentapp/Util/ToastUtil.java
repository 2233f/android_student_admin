package com.example.studentapp.Util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
   public static void showToast(Context context, String message){
       Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
   }
}

