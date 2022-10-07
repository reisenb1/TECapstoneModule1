package com.techelevator.Model;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

public class VendingMachineTest {


    @Test
    public void initialLoad() {
        //Arrange
        try {
            VendingMachine vendingMachine = new VendingMachine();
        } catch (IOException e) {
            System.out.println("Unable to create log");
        }
        //Act

        //Assert

    }

    @Test
    public void insertBills() {
        //Arrange
        VendingMachine insertBillTest = null;
        try {
            insertBillTest = new VendingMachine();
        } catch (IOException e) {
            System.out.println("Unable to create log");
        }
        BigDecimal actual = new BigDecimal(3);
        BigDecimal actual1 = new BigDecimal(4);
        BigDecimal actual2 = new BigDecimal(6);

        //Act
        BigDecimal money = null;
        try {
            money = insertBillTest.insertBills(3);
        } catch (FileNotFoundException e) {
            System.out.println("Log File not found");
        }
        BigDecimal money1 = null;
        try {
            money1 = insertBillTest.insertBills(1);
        } catch (FileNotFoundException e) {
            System.out.println("Log File not found");
        }
        BigDecimal money2 = null;
        try {
            money2 = insertBillTest.insertBills(2);
        } catch (FileNotFoundException e) {
            System.out.println("Log File not found");
        }


        //Assert
        Assert.assertEquals(0, actual.compareTo(money));
        Assert.assertEquals(0, actual1.compareTo(money1));
        Assert.assertEquals(0, actual2.compareTo(money2));



    }

    @Test
    public void finishTransaction() {
        //Arrange
        VendingMachine finishTransactionTest = null;
        try {
            finishTransactionTest = new VendingMachine();
        } catch (IOException e) {
            System.out.println("Unable to create log");
        }
        BigDecimal payment1 = new BigDecimal(10);
        BigDecimal payment2 = new BigDecimal(5.35);
        BigDecimal payment3 = new BigDecimal(4.40);

        int[] expectedArray1 = {40, 0, 0};
        int[] expectedArray2 = {21, 1, 0};
        int[] expectedArray3 = {17, 1, 1};

        //Act
        int[] change1 = new int[0];
        try {
            change1 = finishTransactionTest.finishTransaction(payment1);
        } catch (FileNotFoundException e) {
            System.out.println("Log File not found");

        }
        int[] change2 = new int[0];
        try {
            change2 = finishTransactionTest.finishTransaction(payment2);
        } catch (FileNotFoundException e) {
            System.out.println("Log File not found");

        }
        int[] change3 = new int[0];
        try {
            change3 = finishTransactionTest.finishTransaction(payment3);
        } catch (FileNotFoundException e) {
            System.out.println("Log File not found");

        }


        //Assert
        Assert.assertArrayEquals(expectedArray1, change1);
        Assert.assertArrayEquals(expectedArray2, change2);
        Assert.assertArrayEquals(expectedArray3, change3);

    }
}
