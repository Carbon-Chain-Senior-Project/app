package com.example.turner.carbonchainpos_v10;

/**
 * Created by Turner on 3/24/2018.
 */

public class Product {
    String name;
    double value;

    Product(String name, double value){
        this.name = name;
        this.value = value;
    }

    String getName(){
        return name;
    }

    double getValue(){
        return value;
    }

}