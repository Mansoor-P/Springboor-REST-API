package org.AOP;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {
    public void Checkout(){
        System.out.println("Checkout Method from ShoppingCart Called");
    }

}
