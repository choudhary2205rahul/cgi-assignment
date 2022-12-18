
import {Component, OnInit} from '@angular/core';
import {RecipeService} from "../../service/recipe.service";
import {MatTableDataSource} from "@angular/material/table";
import {Recipe} from "../../models/recipe";

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit{

  displayedColumns: string[] = ['title','href','ingredients','thumbnail'];

  recipes: Recipe[] = [];
  dataSource = new MatTableDataSource(this.recipes);

  constructor(public recipeService: RecipeService) {
  }

  ngOnInit(): void {
    this.getRecipes();
  }

  getRecipes() {
    this.recipeService.getRecipes().subscribe(recipes => {
      this.recipes = recipes;

      this.dataSource = new MatTableDataSource(this.recipes);
    });
  }

  getRecipesForIngredients(ingredientsInput: string) {
    this.recipeService.getRecipesForIngredients(ingredientsInput).subscribe(recipes => {
      this.recipes = recipes;
      this.dataSource = new MatTableDataSource(this.recipes);
    }, error => {
      this.recipes = [];
      this.dataSource = new MatTableDataSource(this.recipes);
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    //this.dataSource.filter = filterValue.trim().toLowerCase();
    let ingredientsInput = filterValue.trim().toLowerCase();
    if (ingredientsInput.length === 0) {
      this.getRecipes();
    } else {
      this.getRecipesForIngredients(ingredientsInput);
    }

  }

}
