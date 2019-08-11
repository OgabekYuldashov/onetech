package com.mum.onetech.util;

import java.text.DecimalFormat;

public class Util {

    public static boolean isPositiveInteger(String strNum) {
        if (strNum == null) return false;
        return strNum.matches("^([1-9])+([0-9])*$");
    }


    public static DecimalFormat df2 = new DecimalFormat("#.##");
}
