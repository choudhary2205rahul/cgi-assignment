package com.cgi.assignment.recipe.business.impl;

import com.cgi.assignment.recipe.business.RecipeService;
import com.cgi.assignment.recipe.dto.Recipe;
import com.cgi.assignment.recipe.exception.RecipeNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    List<Recipe> recipes = new ArrayList<>();

    public RecipeServiceImpl() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File recipesFile = new ClassPathResource("recipe.json").getFile();
            Recipe[] recipesList = mapper.readValue(recipesFile, Recipe[].class);
            recipes = Arrays.asList(recipesList);
        } catch (IOException e) {
            log.error("Error file reading recipe json from resources {}", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Recipe> findAll() {
        if(recipes.size() < 1) {
            throw new RecipeNotFoundException("Recipes not found");
        }
        Collections.sort(recipes, Comparator.comparing(Recipe::getTitle));
        return recipes;
    }

    @Override
    public List<Recipe> findRecipesWithIngredients(List<String> ingredients) {
        List<Recipe> recipesWithIngredients = new ArrayList<>();
         recipes.forEach(recipe -> {
            recipe.getIngredients().forEach(ingredient -> {
                ingredients.forEach(ing -> {
                    if(ingredient.getName().startsWith(ing)) {
                        recipesWithIngredients.add(recipe);
                    }
                });
            });
         });

         if(recipesWithIngredients.size() < 1) {
             throw new RecipeNotFoundException("Recipes not found with given ingredients " + ingredients );
         }
        Collections.sort(recipesWithIngredients, Comparator.comparing(Recipe::getTitle));
         return recipesWithIngredients;
    }
}
