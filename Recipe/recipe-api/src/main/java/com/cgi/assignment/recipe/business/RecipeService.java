package com.cgi.assignment.recipe.business;

import com.cgi.assignment.recipe.dto.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> findAll();

    List<Recipe> findRecipesWithIngredients(List<String> ingredients);
}
