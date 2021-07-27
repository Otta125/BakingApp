package com.pop.bakingapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.pop.bakingapp.Utilities.Constants;
import com.pop.bakingapp.Utilities.IngredientsWidgetService;
import com.pop.bakingapp.Utilities.MyWidgetRemoteViewsService;

import static com.pop.bakingapp.Utilities.IngredientsWidgetService.EXTRA_INGREDINT_ID;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWideget extends AppWidgetProvider {
    static Context mCx;
    static Intent serviceIntent;

    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager, int recipe_id, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipe_id, appWidgetId);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int id,
                                int appWidgetId) {
        mCx = context;

        RemoteViews views = new RemoteViews(
                context.getPackageName(),
                R.layout.ingredients_wideget);
        if (serviceIntent != null) {

            mCx.stopService(serviceIntent);
            serviceIntent = null;
        }
        Intent intent = new Intent(context, MainActivity.class);
        serviceIntent = new Intent(context, MyWidgetRemoteViewsService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        serviceIntent.putExtra(EXTRA_INGREDINT_ID, id);
        serviceIntent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        views.setRemoteAdapter(R.id.widgetListView, serviceIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews views = new RemoteViews(
                context.getPackageName(),
                R.layout.ingredients_wideget);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int MY_ID = preferences.getInt(Constants.Recipe_ID_FOR_Widget, 0);
        Intent intent = new Intent(context, MainActivity.class);
        serviceIntent = new Intent(context, MyWidgetRemoteViewsService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds);
        serviceIntent.putExtra(EXTRA_INGREDINT_ID, MY_ID);
        serviceIntent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        views.setRemoteAdapter(R.id.widgetListView, serviceIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, views);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
  /*      if (action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            // refresh all your widgets
            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            ComponentName cn = new ComponentName(context, IngredientsWideget.class);
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.widgetListView);

        }*/
        super.onReceive(context, intent);
    }
}

