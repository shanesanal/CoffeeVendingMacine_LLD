package com.ecommerce;

public class Main {
    public static void main(String[] args)
    {
        CoffeeMachine coffeeMachine=new CoffeeMachine();

        coffeeMachine.displayMenu();

        Coffee espresso = coffeeMachine.selectCoffee("Espresso");
        coffeeMachine.dispenseCoffee(espresso,new Payment(2.5));

        Coffee americano = coffeeMachine.selectCoffee("Americano");
        coffeeMachine.dispenseCoffee(americano,new Payment(3.0));

        Coffee cappuccino = coffeeMachine.selectCoffee("Cappuccino");
        coffeeMachine.dispenseCoffee(cappuccino,new Payment(3.5));


    }
}