package com.techelevator;

import com.techelevator.Model.VendingMachine;

import java.io.FileNotFoundException;

public class ScratchPad {

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        try {
            vendingMachine.initialLoad();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
