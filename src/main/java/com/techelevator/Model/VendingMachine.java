package com.techelevator.Model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class VendingMachine {

    private BigDecimal payment;
    private BigDecimal bankOverall;
    private Map<String,Product> productMap = new TreeMap<>();

    public VendingMachine(){
       payment = new BigDecimal(0.0);
       bankOverall = new BigDecimal(0.0);

    }
}


