package com.techelevator.Model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class VendingMachineTest {


    @Test
    public void initialLoad() {
        //Arrange
        VendingMachine vendingMachine = new VendingMachine();
        //Act

        //Assert

    }

    @Test
    public void insertBills() {
        //Arrange
        VendingMachine insertBillTest = new VendingMachine();
        BigDecimal actual = new BigDecimal(3);
        BigDecimal actual1 = new BigDecimal(4);
        BigDecimal actual2 = new BigDecimal(6);

        //Act
        BigDecimal money = insertBillTest.insertBills(3);
        BigDecimal money1 = insertBillTest.insertBills(1);
        BigDecimal money2 = insertBillTest.insertBills(2);


        //Assert
        Assert.assertEquals(0, actual.compareTo(money));
        Assert.assertEquals(0, actual1.compareTo(money1));
        Assert.assertEquals(0, actual2.compareTo(money2));



    }

    @Test
    public void finishTransaction() {
        //Arrange
        VendingMachine finishTransactionTest = new VendingMachine();
        BigDecimal payment1 = new BigDecimal(10);
        BigDecimal payment2 = new BigDecimal(5.35);
        BigDecimal payment3 = new BigDecimal(4.40);

        int[] expectedArray1 = {40, 0, 0};
        int[] expectedArray2 = {21, 1, 0};
        int[] expectedArray3 = {17, 1, 1};

        //Act
        int[] change1 = finishTransactionTest.finishTransaction(payment1);
        int[] change2 = finishTransactionTest.finishTransaction(payment2);
        int[] change3 = finishTransactionTest.finishTransaction(payment3);


        //Assert
        Assert.assertArrayEquals(expectedArray1, change1);
        Assert.assertArrayEquals(expectedArray2, change2);
        Assert.assertArrayEquals(expectedArray3, change3);

    }
}
