import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Recipe} from "../models/recipe";

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  constructor(public http: HttpClient) { }

  getRecipes(): Observable<Recipe[]> {
    const href = 'http://localhost:8080/api/recipes';
    const requestUrl = `${href}`;
    return this.http.get<Recipe[]>(requestUrl);
  }

  getRecipesForIngredients(ingredients: string) {
    const href = 'http://localhost:8080/api/recipes/filter?ingredients=';
    const requestUrl = `${href}${ingredients}`;
    return this.http.get<Recipe[]>(requestUrl);
  }
}
