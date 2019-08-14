package com.mum.onetech.util;

import java.text.DecimalFormat;
import java.util.UUID;

public class Util {

    public static boolean isPositiveInteger(String strNum) {
        if (strNum == null) return false;
        return strNum.matches("^([1-9])+([0-9])*$");
    }


    public static DecimalFormat df2 = new DecimalFormat("#.##");


    public static String randomUUID() {
        // Creating a random UUID (Universally unique identifier)
        return UUID.randomUUID().toString();
    }

}
