package com.desai.vatsal.mydynamiccalendarexample;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate extends Date {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /*
     * additional constructors
     */

    @Override
    public String toString() {
        return dateFormat.format(this);
    }
}