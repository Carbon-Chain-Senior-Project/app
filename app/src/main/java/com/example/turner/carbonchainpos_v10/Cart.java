package com.example.turner.carbonchainpos_v10;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Cart {
    Map<Product, Integer> cart;
    double value = 0;

    Cart(){
        cart = new LinkedHashMap<>();
    }

    void addToCart(Product product){
        if(cart.containsKey(product))
            cart.put(product, cart.get(product) + 1);
        else
            cart.put(product, 1);

        value += product.getValue();
    }

    int getQuantity(Product product){
        return cart.get(product);
    }

    Set getProducts(){
        return cart.keySet();
    }

    void empty(){
        cart.clear();
        value = 0;
    }

    double getValue(){
        return value;
    }

    int getSize(){
        return cart.size();
    }
}
