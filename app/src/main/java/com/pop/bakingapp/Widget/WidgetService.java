package com.pop.bakingapp.Widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.pop.bakingapp.MyIngredientWidget;
import com.pop.bakingapp.Utilities.Constants;

public class WidgetService extends IntentService {

    public static final String ACTION_UPDATE_INGREDIENT_WIDGETS =
            "com.pop.bakingapp.Widget.update_ingredient_widgets";


    public WidgetService() {
        super("name");
    }
    public static void startActionWaterPlant(Context context) {
        Intent intent = new Intent(context, WidgetService.class);
        intent.setAction(ACTION_UPDATE_INGREDIENT_WIDGETS);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_INGREDIENT_WIDGETS.equals(action)) {
                handleUpdateWidget();
            }
        }
    }

    private void handleUpdateWidget() {

        SharedPreferences prefs = getSharedPreferences(Constants.Recipe_ID_FOR_Widget, MODE_PRIVATE);
        String name = prefs.getString(Constants.Recipe_ID_FOR_Widget, "No name defined");//"No name defined" is the default value.
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetids = appWidgetManager.getAppWidgetIds(new ComponentName(this, MyIngredientWidget.class));

        MyIngredientWidget.updatePlantWidgets(this, appWidgetManager, name, appWidgetids);
    }

}