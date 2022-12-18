package com.cgi.assignment.recipe.api;

import com.cgi.assignment.recipe.business.impl.RecipeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class RecipeApiTest {

    private static MockHttpServletRequest request;

    @Mock
    RecipeServiceImpl recipeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

    @BeforeAll
    public static void setup() {
        request = new MockHttpServletRequest();
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }

    @Test
    void findRecipesWithIngredients() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/filter?ingredients=onions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }

    @Test
    void findRecipesWithIngredients_404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/filter?ingredients=pickle"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }
}