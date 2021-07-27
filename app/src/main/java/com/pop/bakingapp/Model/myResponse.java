package com.pop.bakingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class myResponse {

    public List<RecipesResponse> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipesResponse> ingredients) {
        this.ingredients = ingredients;
    }

    @SerializedName("ingredients")
    @Expose
    private List<RecipesResponse> ingredients = null;
}
