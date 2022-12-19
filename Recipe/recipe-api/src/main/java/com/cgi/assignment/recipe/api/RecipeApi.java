package com.cgi.assignment.recipe.api;

import com.cgi.assignment.recipe.business.RecipeService;
import com.cgi.assignment.recipe.dto.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/recipes")
@Slf4j
public class RecipeApi {

    private RecipeService recipeService;

    public RecipeApi(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> findAll() {
        return new ResponseEntity<>(recipeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Recipe>> findRecipesWithIngredients(@Valid @RequestParam List<String> ingredients) {
        return new ResponseEntity<>(recipeService.findRecipesWithIngredients(ingredients), HttpStatus.OK);
    }

}
