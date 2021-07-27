package com.pop.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pop.bakingapp.Activities.RecipeStepsActivity;
import com.pop.bakingapp.Model.RecipesResponse;
import com.pop.bakingapp.R;
import com.pop.bakingapp.Utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipesViewHolder> {

    private Context mCtx;
    private List<RecipesResponse> RecipesList;


        public RecipeAdapter(Context mCtx, List<RecipesResponse> RecipesList) {
        this.mCtx = mCtx;
        this.RecipesList = RecipesList;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_row, parent, false);
        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipesViewHolder holder, final int position) {

        if (!RecipesList.get(position).getImage().isEmpty()) {
            Picasso.get()
                    .load(RecipesList.get(position).getImage())
                    .placeholder(R.drawable.icon_recipe)
                    .placeholder(R.drawable.icon_recipe)
                    .fit()
                    .into(holder.img);
        }

        holder.Recipetxt.setText(RecipesList.get(position).getName());
        holder.Servingtxt.setText(String.valueOf(RecipesList.get(position).getServings()));
        holder.RecipeLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, RecipeStepsActivity.class);
                intent.putExtra(Constants.RECIPE_ID, RecipesList.get(position).getId());

                Toast.makeText(mCtx,  RecipesList.get(position).getName(), Toast.LENGTH_SHORT).show();
                intent.putExtra(Constants.RECIPE_NAME, RecipesList.get(position).getName());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return RecipesList.size();
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder {
        CardView RecipeLin;
        TextView Recipetxt, Servingtxt;
        ImageView img;

        public RecipesViewHolder(View itemview) {
            super(itemview);
            RecipeLin = itemView.findViewById(R.id.Lin);
            Recipetxt = itemView.findViewById(R.id.txtRcip);
            Servingtxt = itemView.findViewById(R.id.txtServ);
            img = itemView.findViewById(R.id.img_id);
        }
    }
}
