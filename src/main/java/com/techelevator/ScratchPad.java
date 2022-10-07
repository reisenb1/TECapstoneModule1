package com.techelevator;

import com.techelevator.Model.VendingMachine;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScratchPad {

    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyy HH:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

}


