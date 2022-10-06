package com.techelevator.Model;

import java.math.BigDecimal;

public class Candy extends Product{

    public Candy(String slot, String name, BigDecimal price) {
        super(slot, name, price, "Munch Munch, Yum!");
    }
}
