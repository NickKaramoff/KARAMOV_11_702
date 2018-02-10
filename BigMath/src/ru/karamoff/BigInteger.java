package ru.karamoff;

import java.util.Arrays;

public class BigInteger {

    private int[] digits;


    public BigInteger(String input) {
        this.digits = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            digits[i] = input.charAt(input.length() - i - 1) - '0';
        }
    }

    public BigInteger(int[] input) {
        this.digits = input;
    }

    public BigInteger(int input) {
        new BigInteger((long) input);
    }

    public BigInteger(long input) {
        digits = new int[String.valueOf(input).length()];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = (int) input % 10;
            input /= 10;
        }
    }

    @Override
    public String toString() {
        if (digits.length == 0) {
            return "0";
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = digits.length - 1; i >= 0; i--) {
                builder.append(digits[i]);
            }
            return builder.toString();
        }
    }

    public void add(BigInteger num) {
        BigInteger orig = new BigInteger(this.digits);

        digits = new int[Math.max(orig.digits.length, num.digits.length) + 1];

        boolean lesserEnd = false;

        for (int i = 0; i < digits.length - 1; i++) {
            if (!lesserEnd && (orig.digits.length == i || num.digits.length == i)) {
                lesserEnd = true;
            }
            if (!lesserEnd) {
                if (orig.digits[i] + num.digits[i] >= 10) {
                    digits[i] += (orig.digits[i] + num.digits[i]) % 10;
                    digits[i + 1]++;
                } else {
                    digits[i] += orig.digits[i] + num.digits[i];
                }
            } else {
                if (orig.digits.length > num.digits.length) { //TODO less if-checks
                    digits[i] += orig.digits[i];
                } else {
                    digits[i] += num.digits[i];
                }
            }
        }
        cutOffZeros();
    }

//    public void multiply(BigInteger num) {
//        BigInteger orig = new BigInteger(this.digits);
//
//        digits = new int[orig.digits.length + num.digits.length];
//
//
//
//        cutOffZeros();
//    }

    private void cutOffZeros() {
        int zeroSince = -1;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == 0 && zeroSince == -1) {
                zeroSince = i;
            }
            if (digits[i] != 0 && zeroSince != -1) {
                zeroSince = -1;
            }
        }

        if (zeroSince != -1) {
            digits = Arrays.copyOf(digits, zeroSince);
        }
    }
}
