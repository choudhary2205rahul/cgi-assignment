package com.cgi.assignment.recipe.business.impl;

import com.cgi.assignment.recipe.exception.RecipeNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipeServiceImplTest {

    @Autowired
    RecipeServiceImpl recipeService;

    @Test
    void findAll() {
        assertEquals(40, recipeService.findAll(page, size).size());
    }

    @Test
    void findRecipesWithIngredients() {
        List<String> ingredients = Arrays.asList("onions");
        assertEquals(11, recipeService.findRecipesWithIngredients(ingredients).size());
    }


    @Test
    void findRecipesWithIngredients_notfound() {
        List<String> ingredients = Arrays.asList("popcorn");
        assertThrows(RecipeNotFoundException.class,
                () -> recipeService.findRecipesWithIngredients(ingredients),
                "Recipes not found");
    }
}