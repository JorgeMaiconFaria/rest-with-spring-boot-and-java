package com.github.jorgemaicon.util;

import com.github.jorgemaicon.exception.UnsupportedMathOperationException;

public class ConvertToDouble {

    static public Double convertToDouble(String strNumber) throws UnsupportedMathOperationException {
        if(strNumber == null || strNumber.isEmpty()) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return Double.parseDouble(strNumber);
    }
}
