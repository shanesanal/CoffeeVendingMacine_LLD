package com.ecommerce;

import java.util.Map;

public class Coffee {

    private final String name;
    private final double price;
    Map<Ingredient,Integer> recipe;

    public Coffee(String name, double price, Map<Ingredient,Integer> recipe) {
        this.name = name;
        this.price = price;
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public Map<Ingredient, Integer> getRecipe() {
        return recipe;
    }

    public double getPrice() {
        return price;
    }
}
