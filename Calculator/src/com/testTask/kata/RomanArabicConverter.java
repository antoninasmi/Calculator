package com.testTask.kata;

import java.util.List;

class RomanArabicConverter {

    public int romanToArabic(String numeral) {
        String romanNum = numeral.toUpperCase();
        int res = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNum.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNum.startsWith(symbol.name())) {
                res += symbol.getValue();
                romanNum = romanNum.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNum.length() > 0) {
            throw new IllegalArgumentException(numeral + " cannot be converted to a Roman Numeral");
        }

        return res;
    }

    public String arabicToRoman(int num) {
        if ((num <= 0) || (num > 100)) {
            throw new IllegalArgumentException(num + " is not in range [0, 100]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((num > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= num) {
                sb.append(currentSymbol.name());
                num -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}
