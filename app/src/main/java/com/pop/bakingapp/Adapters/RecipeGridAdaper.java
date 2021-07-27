package com.pop.bakingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pop.bakingapp.Model.RecipesResponse;
import com.pop.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeGridAdaper extends BaseAdapter {
    private final Context mContext;

    private final List<RecipesResponse> recipes;

    public RecipeGridAdaper(Context context, List<RecipesResponse> recipes) {
        this.mContext = context;
        this.recipes = recipes;
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final RecipesResponse recipe = recipes.get(position);
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.recipe_row, null);
        }
        // 3
        final ImageView imageView = convertView.findViewById(R.id.img_id);
        final TextView nameTextView = convertView.findViewById(R.id.txtRcip);
        final TextView authorTextView = convertView.findViewById(R.id.txtServ);

        // 4


        if (!recipe.getImage().isEmpty()) {
            Picasso.get()
                    .load(recipe.getImage())
                    .placeholder(R.drawable.icon_recipe)
                    .placeholder(R.drawable.icon_recipe)
                    .fit()
                    .into(imageView);
        }
        nameTextView.setText(recipe.getName());
        authorTextView.setText(String.valueOf(recipe.getServings()));


        return convertView;
    }
}
