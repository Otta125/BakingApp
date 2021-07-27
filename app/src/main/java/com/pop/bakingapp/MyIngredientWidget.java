package com.pop.bakingapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.pop.bakingapp.Widget.WidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class MyIngredientWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                String ID,int appWidgetId) {

        CharSequence widgetText = ID;
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_ingredient_widget);
        views.setTextViewText(R.id.appwidget_text, ID);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        WidgetService.startActionWaterPlant(context);
    }
    public static void updatePlantWidgets(Context context, AppWidgetManager appWidgetManager,
                                          String ID, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, ID, appWidgetId);
        }
        WidgetService.startActionWaterPlant(context);
    }
    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

