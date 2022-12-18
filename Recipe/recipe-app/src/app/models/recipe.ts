import {Ingredient} from "./ingredient";

export class Recipe {
  constructor(
    public title: string,
    public href: string,
    public ingredients: Ingredient[],
    public thumbnail: string
  ){

  }
}
