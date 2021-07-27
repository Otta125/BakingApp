package com.pop.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pop.bakingapp.Activities.RecipeStepsActivity;
import com.pop.bakingapp.Model.Ingredient;
import com.pop.bakingapp.Model.RecipesResponse;
import com.pop.bakingapp.R;
import com.pop.bakingapp.Utilities.Constants;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.RecipesViewHolder> {

    private Context mCtx;
    private List<Ingredient> RecipesList;


    public IngredientsAdapter(Context mCtx, List<Ingredient> RecipesList) {
        this.mCtx = mCtx;
        this.RecipesList = RecipesList;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_row,
                parent, false);
        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipesViewHolder holder, final int position) {

        holder.Ingrientstxt.setText(RecipesList.get(position).getIngredient());
        holder.Quantity.setText(RecipesList.get(position).getQuantity()+" "+RecipesList.get(position).getMeasure());
    }


    @Override
    public int getItemCount() {
        return RecipesList.size();
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder {
       // LinearLayout RecipeLin;
        TextView Ingrientstxt,Quantity;

        public RecipesViewHolder(View itemview) {
            super(itemview);
            Quantity = itemView.findViewById(R.id.txt_quantity);
            Ingrientstxt = itemView.findViewById(R.id.txt_Ingredient);
        }
    }
}
