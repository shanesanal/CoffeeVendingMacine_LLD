package com.ecommerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachine {
    private final List<Coffee> coffeeMenu;
    private final Map<String,Ingredient> ingredients;


    private static CoffeeMachine instance = null;

    private static CoffeeMachine getInstance() {
        if(instance == null) {

            synchronized (CoffeeMachine.class)
            {
                if(instance == null) {

                    instance = new CoffeeMachine();
                }
            }

    }
        return instance;
    }

    public CoffeeMachine() {
     coffeeMenu = new ArrayList<>();
     ingredients = new HashMap<>();
        initializeIngredients();
        initializeCoffeeMenu();

    }

    public void initializeCoffeeMenu()
    {
        Map<Ingredient,Integer> espressoRecipie = new HashMap<>();
        espressoRecipie.put(ingredients.get("Coffee"), 1);
        espressoRecipie.put(ingredients.get("Water"), 1);
        coffeeMenu.add(new Coffee("Espresso", 2.5, espressoRecipie));

        Map<Ingredient,Integer> americanoRecipie = new HashMap<>();
        americanoRecipie.put(ingredients.get("Coffee"), 1);
        americanoRecipie.put(ingredients.get("Water"), 1);
        americanoRecipie.put(ingredients.get("Milk"),1);
        coffeeMenu.add(new Coffee("Americano", 3.0, americanoRecipie));

        Map<Ingredient,Integer> cappuccinoRecipie = new HashMap<>();
        cappuccinoRecipie.put(ingredients.get("Coffee"), 1);
        cappuccinoRecipie.put(ingredients.get("Water"), 1);
        cappuccinoRecipie.put(ingredients.get("Milk"),2);

        coffeeMenu.add(new Coffee("Cappuccino", 3.5, cappuccinoRecipie));

    }

    public void initializeIngredients()
    {
        ingredients.put("Coffee", new Ingredient("Coffee", 10));
        ingredients.put("Water", new Ingredient("Water", 10));
        ingredients.put("Milk", new Ingredient("Milk", 10));
    }

    public void displayMenu()
    {
        System.out.println("Coffee Menu:");
        for(Coffee coffee: coffeeMenu)
        {
            System.out.println(coffee.getName() + " - $" + coffee.getPrice());
        }
    }

    public synchronized Coffee selectCoffee(String coffeeName)
    {
        for(Coffee coffee: coffeeMenu)
        {
            if(coffee.getName().equals(coffeeName))
            {
                return coffee;
            }
        }
        return null;
    }

    public synchronized void dispenseCoffee(Coffee coffee , Payment payment) {

        if (payment.getAmount() >= coffee.getPrice()) {
           if(hasEnoughIngredients(coffee)){
               updateIngredients(coffee);
                System.out.println("Dispensing: " + coffee.getName());
                double change = payment.getAmount() - coffee.getPrice();

                if(change > 0) {
                    System.out.println("Change: $" + change);
                }
              } else {
                System.out.println("Out of stock"+ coffee.getName());

           }

           }
        else {
            System.out.println("Insufficient payment" + coffee.getName());
        }
    }

    public boolean hasEnoughIngredients(Coffee coffee)
    {
        for(Map.Entry<Ingredient,Integer> entry: coffee.getRecipe().entrySet())
        {
            Ingredient ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();
            if(ingredient.getQuantity() < requiredQuantity)
            {
                return false;
            }
        }
        return true;
    }
    
    public void updateIngredients(Coffee coffee)
    {
        for(Map.Entry<Ingredient,Integer> entry: coffee.getRecipe().entrySet())
        {
            Ingredient ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();
            ingredient.updateQuantity(-requiredQuantity);
            
            if(ingredient.getQuantity()<3)
            {
                System.out.println("Low stock for ingredient: " + ingredient.getName());
            }
        }
    }



}
