package com.iparty.Utilities;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by Maurício Generoso on 18/10/2018
 */
public class Storage {


    public static void set(Context context, String key, String value){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(key,value)
                .apply();
    }

    public static String get(Context context, String key){
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(key,"");
    }
}
