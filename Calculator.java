package com.testTask.kata;

class Calculator {
    private static final String operations = "-+*/";
    private final RomanArabicConverter converter;

    public Calculator() {
        converter = new RomanArabicConverter();
    }

    public String calc(String input) throws Exception {
        input = input.toUpperCase().replaceAll(" ", ""); //нормализуем строку
        String[] operands = input.split("[" + operations + "]");
        String operator = input.replaceAll("[^" + operations + "]", "");

        if(operator.length() != 1 | operands.length != 2) throw new Exception("wrong operator or operands (length)");

        boolean isRomanO1 = isRoman(operands[0]);
        boolean isRomanO2 = isRoman(operands[1]);

        boolean isArabicO1 = isArabic(operands[0]);
        boolean isArabicO2 = isArabic(operands[1]);

        if((isRomanO1 & isArabicO2) | (isRomanO2 & isArabicO1)) throw new Exception("different encoding");

        if(isArabicO1 & isArabicO2) {
            int o1 = Integer.parseInt(operands[0]);
            int o2 = Integer.parseInt(operands[1]);
            if ((o1 > 0 & o1 < 11) & (o2 > 0 & o2 < 11)) {

                int res = calculate(o1, o2, operator);
                return String.valueOf(res);
            }else{
                throw new Exception("operand(s) out of range");
            }
        }

        if(isRomanO1 & isRomanO2) {
            int o1 = converter.romanToArabic(operands[0]);
            int o2 = converter.romanToArabic(operands[1]);
            if ((o1 < 1 | o1 > 10) | (o2 < 1 | o2 > 10)){
                throw new Exception("operand(s) out of range");
            }else {

                int res = calculate(o1, o2, operator);
                if (res <= 0) {
                    throw new Exception("roman numerals cannot be negative or zero");
                }
                return converter.arabicToRoman(res);
            }
        }
            throw new Exception("some errors occurred");

    }

    public int calculate (int num1, int num2, String op) {
        int result = 0;
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                try {
                    result = num1 / num2;
                } catch (ArithmeticException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("only integer non-zero parameters allowed");
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("wrong operand");
        }
        return result;
    }

    private boolean isArabic(String num){
        try{
            Integer.parseInt(num);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    private boolean isRoman(String num){
        String s = num.replaceAll("[XLIVC]", "");
        return !num.isEmpty() & s.isEmpty();
    }
}
