package com.pop.bakingapp.Fragments;


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pop.bakingapp.Activities.RecipeStepsActivity;
import com.pop.bakingapp.Adapters.IngredientsAdapter;
import com.pop.bakingapp.Adapters.StepsAdapter;
import com.pop.bakingapp.Model.Ingredient;
import com.pop.bakingapp.Model.RecipesResponse;
import com.pop.bakingapp.Model.Step;
import com.pop.bakingapp.Model.myResponse;
import com.pop.bakingapp.R;
import com.pop.bakingapp.Utilities.Constants;
import com.pop.bakingapp.Widget.WidgetService;
import com.pop.bakingapp.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static android.content.Context.MODE_PRIVATE;
import static com.pop.bakingapp.Utilities.SharedPrefs.saverecipesResponseSharedPref;


public class RecipeStepsFragment extends Fragment
        //Interface
        implements RecipeStepsActivity.FragmentInterface {

    RecyclerView IngredRecycler, StepsRecycler;
    IngredientsAdapter ingredientsAdapter;
    StepsAdapter stepsAdapter;
    RecipesResponse MyRecipeResponse;
    List<Ingredient> RecipesList;
    List<Step> StepsList;
    int ID;
    private List<RecipesResponse> RecipeList;
    Context mCtx;
    Intent intents;

    public RecipeStepsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RecipeStepsActivity) getActivity()).setOnDataListener(RecipeStepsFragment.this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

        mCtx = view.getContext();
        RecipeList = new ArrayList<>();
        IngredRecycler = view.findViewById(R.id.ingredients_recycler);
        StepsRecycler = view.findViewById(R.id.step_recycler);
        IngredRecycler.setLayoutManager(new LinearLayoutManager(mCtx));
        StepsRecycler.setLayoutManager(new LinearLayoutManager(mCtx));
        RecipesList = new ArrayList<>();
        StepsList = new ArrayList<>();
        GetRecipes();

        return view;
    }

    public void GetRecipes() {
        ///////
        Call<List<RecipesResponse>> call = RetrofitClient.getInstance().getApi().RecipesApi();
        call.enqueue(new Callback<List<RecipesResponse>>() {
            @Override
            public void onResponse(Call<List<RecipesResponse>> call, Response<List<RecipesResponse>> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (!response.body().isEmpty()) {
                            RecipeList.addAll(response.body());
                            myResponse response1 = new myResponse();
                            response1.setIngredients(RecipeList);
                            saverecipesResponseSharedPref(mCtx, response1);
                            for (int i = 0; i < RecipeList.size(); i++) {
                                if (RecipeList.get(i).getId() == ID) {
                                    MyRecipeResponse = RecipeList.get(i);
                                    RecipesList.addAll(MyRecipeResponse.getIngredients());
                                    StepsList.addAll(MyRecipeResponse.getSteps());
                                    stepsAdapter = new StepsAdapter(mCtx, StepsList);
                                    ingredientsAdapter = new IngredientsAdapter(mCtx, RecipesList);

                                    StringBuilder sb = new StringBuilder(1000);
                                    for (int x = 0; x < RecipesList.size(); x++) {
                                        sb.append(RecipesList.get(x).getQuantity())
                                                .append(" ")
                                                .append(RecipesList.get(x).getMeasure())
                                                .append(" ")
                                                .append(RecipesList.get(x).getIngredient())
                                                .append("\n");
                                    }
                                    SharedPreferences.Editor editor = mCtx.getSharedPreferences(Constants.Recipe_ID_FOR_Widget, MODE_PRIVATE).edit();
                                    editor.putString(Constants.Recipe_ID_FOR_Widget, sb.toString());
                                    editor.apply();
                                    WidgetService.startActionWaterPlant(mCtx);
                                    IngredRecycler.setAdapter(ingredientsAdapter);
                                    StepsRecycler.setAdapter(stepsAdapter);
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RecipesResponse>> call, Throwable t) {
                Toast.makeText(mCtx,
                        "there is error check yor internet please", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    //Interface
    @Override
    public void sendDataMethod(int ID) {
        this.ID = ID;




    }
}
