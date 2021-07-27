package com.pop.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.pop.bakingapp.Adapters.RecipeAdapter;
import com.pop.bakingapp.Model.RecipesResponse;
import com.pop.bakingapp.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<RecipesResponse> RecipeList;
    Context mCtx;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCtx = this;
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(mCtx));

        RecipeList = new ArrayList<>();
        GetRecipes();
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
                            RecipeAdapter recipeAdapter = new RecipeAdapter(mCtx, RecipeList);
                            recyclerView.setAdapter(recipeAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RecipesResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "there is error check yor internet please", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
