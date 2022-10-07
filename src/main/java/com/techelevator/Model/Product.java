package com.techelevator.Model;

import java.math.BigDecimal;

public abstract class Product implements Reaction {

    private final String slot;
    private final String name;
    private final BigDecimal price;
    private int inventory;
    private final String sound;

    public Product(String slot,String name,BigDecimal price, String sound){
        this.slot = slot;
        this.name = name;
        this.price = price;
        inventory = 5;
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    public String getSlot() {
        return slot;
    }

    public String getSound() {
        return sound;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        String output = String.format("%5s %-20.20s $%5.2f %5d remaining %n", slot, name, price, inventory);
        return output;
    }

}
