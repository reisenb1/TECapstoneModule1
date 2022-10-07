package com.techelevator.Model;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine {

    private BigDecimal payment;
    private Map<String,Product> productMap = new TreeMap<>();
    private final String inputFilePath = "vendingmachine.csv";
    private File log = new File("Log.log");
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");


    public VendingMachine() throws IOException {

        payment = new BigDecimal(0.0);
        if (log.exists()) {
            log.delete();
            log.createNewFile();
        }else {
            log.createNewFile();
        }
    }


    public BigDecimal insertBills(int moneyPaid) throws FileNotFoundException {
        payment = payment.add(new BigDecimal(moneyPaid));
        BigDecimal paymentDoubleRounded = payment.setScale(2, RoundingMode.HALF_UP);
        double moneyPaidDouble = (double)moneyPaid;
        LocalDateTime now = LocalDateTime.now();
        String logMessage = String.format("%22s FEED MONEY: $%.2f $%.2f %n", dtf.format(now), moneyPaidDouble, paymentDoubleRounded);
        boolean append = log.exists() ? true : false;
        PrintWriter writer = new PrintWriter(new FileOutputStream(log, append));

        writer.print(logMessage);
        writer.flush();
        writer.close();

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

    public void productSelection(String input) throws FileNotFoundException{

        BigDecimal cost = productMap.get(input).getPrice();

        payment = payment.subtract(cost);

        int remainingInventory = productMap.get(input).getInventory() - 1;

        productMap.get(input).setInventory(remainingInventory);
        LocalDateTime now = LocalDateTime.now();
        String logMessage = String.format("%22s %s %2s $%.2f $%.2f %n", dtf.format(now), productMap.get(input).getName(), input, cost, payment);
        boolean append = log.exists() ? true : false;
        PrintWriter writer = new PrintWriter(new FileOutputStream(log, append));
        writer.print(logMessage);
        writer.flush();
        writer.close();


    }

    public int[] finishTransaction(BigDecimal paymentAmount) throws FileNotFoundException{
        BigDecimal paymentAmountCents = paymentAmount.multiply(new BigDecimal(100));

        BigDecimal paymentAmountCentsRounded = paymentAmountCents.setScale(0, RoundingMode.HALF_UP);

        int paymentAmountCentsInt = paymentAmountCentsRounded.intValue();
        int[] change = new int[3];
        //Number of quarters returned
        change[0] = paymentAmountCentsInt / 25;
        //Number of dimes returned
        change[1] = (paymentAmountCentsInt % 25) / 10;
        //Number of nickels returned
        change[2] = ((paymentAmountCentsInt % 25) % 10) / 5;

        payment = new BigDecimal(0);

        LocalDateTime now = LocalDateTime.now();
        String logMessage = String.format("%22s GIVE CHANGE: $%.2f $%.2f %n", dtf.format(now), paymentAmount, payment);
        boolean append = log.exists() ? true : false;
        PrintWriter writer = new PrintWriter(new FileOutputStream(log, append));
        writer.print(logMessage);
        writer.flush();
        writer.close();


        return change;


    }


}


