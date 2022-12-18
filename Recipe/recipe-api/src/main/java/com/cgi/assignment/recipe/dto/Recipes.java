package com.cgi.assignment.recipe.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Recipes {

    private List<Recipe> recipes;
    private Integer recipe_count;
}
