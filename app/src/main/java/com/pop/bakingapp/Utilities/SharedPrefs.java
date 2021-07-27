package com.pop.bakingapp.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.pop.bakingapp.Model.myResponse;

public class SharedPrefs {
    public static final String SHARED_PREF_NAME = "DOC";
    public static final String COMPANY_DETAILS_STRING = "COMPANY_DETAIL";

    public static void saverecipesResponseSharedPref(Context mContext, myResponse response){
        SharedPreferences mPrefs = mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(response);
        prefsEditor.putString(COMPANY_DETAILS_STRING, json);
        prefsEditor.commit();
    }

    public static myResponse getrecipesResponseSharedPref(Context mContext){
        SharedPreferences mPrefs = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = mPrefs.getString(COMPANY_DETAILS_STRING, "");
        if(json.equalsIgnoreCase("")){
            return null;
        }
        myResponse obj = gson.fromJson(json, myResponse.class);
        return obj;
    }
}
