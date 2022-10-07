package com.techelevator.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine {

    private BigDecimal payment;
    private BigDecimal bankOverall;
    private Map<String,Product> productMap = new TreeMap<>();
    private final String inputFilePath = "vendingmachine.csv";

    public VendingMachine(){
       payment = new BigDecimal(0.0);
       bankOverall = new BigDecimal(0.0);
    }


    public BigDecimal insertBills(int moneyPaid) {
        payment = payment.add(new BigDecimal(moneyPaid));
        return payment;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public Map<String, Product> getProductMap() {
        return productMap;
    }

    public void initialLoad() throws FileNotFoundException{
        File inputFile = new File(inputFilePath);
        Scanner newScan = new Scanner(inputFile);
            while (newScan.hasNextLine()) {
                String inputLine = newScan.nextLine();
                String[] inputLineArray = inputLine.split("\\|");
                if (inputLineArray[3].equals("Drink")) {
                    productMap.put(inputLineArray[0], new Beverages(inputLineArray[0], inputLineArray[1], new BigDecimal(inputLineArray[2])));
                } else if (inputLineArray[3].equals("Chip")) {
                    productMap.put(inputLineArray[0], new Chips(inputLineArray[0], inputLineArray[1], new BigDecimal(inputLineArray[2])));
                } else if (inputLineArray[3].equals("Candy")) {
                    productMap.put(inputLineArray[0], new Candy(inputLineArray[0], inputLineArray[1], new BigDecimal(inputLineArray[2])));
                } else if (inputLineArray[3].equals("Gum")) {
                    productMap.put(inputLineArray[0], new Gum(inputLineArray[0], inputLineArray[1], new BigDecimal(inputLineArray[2])));
                }
            }
    }

    public void productSelection(String input) {

        BigDecimal cost = productMap.get(input).getPrice();

        payment = payment.subtract(cost);

        int remainingInventory = productMap.get(input).getInventory() - 1;

        productMap.get(input).setInventory(remainingInventory);

    }

}


