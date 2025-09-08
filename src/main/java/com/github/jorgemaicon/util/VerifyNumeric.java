package com.github.jorgemaicon.util;

public class VerifyNumeric {

    static public boolean isNumeric(String strNumber) {
        if(strNumber == null || strNumber.isEmpty()) {
            return false;
        }

        String number = strNumber.replace("," , ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
