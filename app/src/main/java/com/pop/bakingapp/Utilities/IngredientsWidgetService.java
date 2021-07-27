package com.pop.bakingapp.Utilities;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.pop.bakingapp.IngredientsWideget;

public class IngredientsWidgetService extends IntentService {

    public static final String EXTRA_INGREDINT_ID = "com.pop.bakingapp.ingredient_id";
    public static final String ACTION_INGREDIENTS_ID = "com.pop.bakingapp.ingredient_action";


    static Context mCx;
    public IngredientsWidgetService() {
        super("m");
    }

    public static void startrecipeid(Context context) {
        mCx =context;


    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INGREDIENTS_ID.equals(action)) {
                 int IngId = intent.getIntExtra(EXTRA_INGREDINT_ID,
                        0);
                handleActionGetID(IngId);

            }
        }


    }

    private void handleActionGetID(int id) {


        Intent intent = new Intent(mCx, IngredientsWideget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
// since it seems the onUpdate() is only fired on that:
        int[]  ids = AppWidgetManager.getInstance(mCx).getAppWidgetIds(new ComponentName(mCx, IngredientsWideget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);

        mCx.sendBroadcast(intent);

    }


}
