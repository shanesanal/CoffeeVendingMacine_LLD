package com.ecommerce;

public class Main {
    public static void main(String[] args)
    {
        CoffeeMachine coffeeMachine=new CoffeeMachine();

        coffeeMachine.displayMenu();

        Coffee espresso = coffeeMachine.selectCoffee("Espresso");
        coffeeMachine.dispenseCoffee(espresso,new Payment(2.5));


    }
}