package com.cgi.assignment.recipe.dto;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private String title;
    private String href;
    private Set<Ingredient> ingredients;
    private String thumbnail;

}
