package com.pop.bakingapp.api;

import com.pop.bakingapp.Model.RecipesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("baking.json")
    Call<List<RecipesResponse>> RecipesApi();
}

