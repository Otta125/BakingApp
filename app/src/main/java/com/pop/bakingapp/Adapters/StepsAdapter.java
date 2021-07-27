package com.pop.bakingapp.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pop.bakingapp.Model.Step;
import com.pop.bakingapp.R;
import com.pop.bakingapp.Utilities.Constants;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.RecipesViewHolder> {

    private Context mCtx;
    private List<Step> RecipesList;
    // Define a new interface OnImageClickListener that triggers a callback in the host activity
    OnClickListener mCallback;

    // OnImageClickListener interface, calls a method in the host activity named onImageSelected
    public interface OnClickListener {
        void OnStepSelected(String Video_url, String Description, String thumbnailURL);
    }

    public StepsAdapter(Context mCtx, List<Step> RecipesList) {
        this.mCtx = mCtx;
        this.RecipesList = RecipesList;
        try {
            mCallback = (OnClickListener) mCtx;
        } catch (ClassCastException e) {
            throw new ClassCastException(mCtx.toString()
                    + " must implement OnImageClickListener");
        }
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

        holder.Ingrientstxt.setText(RecipesList.get(position).getShortDescription());
        holder.RecipeLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(Constants.IMG_ID, RecipesList.get(position).getVideoURL()); // value to store
                editor.apply();


                mCallback.OnStepSelected(RecipesList.get(position).getVideoURL()
                        ,RecipesList.get(position).getDescription(),
                        RecipesList.get(position).getThumbnailURL());
            }
        });
    }
    @Override
    public int getItemCount() {
        return RecipesList.size();
    }

    public class RecipesViewHolder extends RecyclerView.ViewHolder {
        LinearLayout RecipeLin;
        TextView Ingrientstxt,Quantity;

        public RecipesViewHolder(View itemview) {
            super(itemview);
            Quantity = itemView.findViewById(R.id.txt_quantity);
            Ingrientstxt = itemView.findViewById(R.id.txt_Ingredient);
            RecipeLin = itemView.findViewById(R.id.Lin);
        }
    }
}
