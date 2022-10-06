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
}
