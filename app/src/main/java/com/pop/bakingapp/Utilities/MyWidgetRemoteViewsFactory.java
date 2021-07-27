package com.pop.bakingapp.Utilities;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.pop.bakingapp.Model.Ingredient;
import com.pop.bakingapp.Model.myResponse;
import com.pop.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.pop.bakingapp.Utilities.IngredientsWidgetService.EXTRA_INGREDINT_ID;
import static com.pop.bakingapp.Utilities.SharedPrefs.getrecipesResponseSharedPref;

public class MyWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    static Context mContext;
    static List<Ingredient> RecipesList;

    public MyWidgetRemoteViewsFactory(Context applicationContext, Intent intent) {
        mContext = applicationContext;
        RecipesList =new ArrayList<>();
        myResponse response = getrecipesResponseSharedPref(mContext);
        if (intent != null) {
                int IngId = intent.getIntExtra(EXTRA_INGREDINT_ID,
                        0);
                for (int i = 0; i < response.getIngredients().size(); i++) {
                    if (response.getIngredients().get(i).getId() == IngId) {
                        RecipesList.addAll(response.getIngredients().get(i).getIngredients());
                    }
                }
        }

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {


    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return RecipesList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews view = new RemoteViews(mContext.getPackageName(), R.layout.collection_widget_list_item);
        Ingredient ingredient = RecipesList.get(position);
        String ingredientText = ingredient.getQuantity() + " " + ingredient.getMeasure() + " " + ingredient.getIngredient();
        view.setTextViewText(R.id.widgetItemTaskNameLabel, ingredientText);
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
