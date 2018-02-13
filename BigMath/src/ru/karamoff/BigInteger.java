package ru.karamoff;

import java.util.Arrays;

public class BigInteger implements Comparable<BigInteger> {

    private byte[] digits;


    public BigInteger(String input) {
        this.digits = new byte[input.length()];
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(input.length() - i - 1) <= '9' && input.charAt(input.length() - i - 1) >= '0') {
                digits[i] = (byte) (input.charAt(input.length() - i - 1) - '0');
            } else {
                throw new NumberFormatException("Wrong input values! Only digits 0-9 should be present.");
            }
        }
    }

    public BigInteger(byte[] input) {
        this.digits = input;
    }

    public BigInteger(short input) {
        new BigInteger((long) input);
    }

    public BigInteger(int input) {
        new BigInteger((long) input);
    }

    public BigInteger(long input) {
        digits = new byte[String.valueOf(input).length()];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = (byte) (input % 10);
            input /= 10;
        }
    }


    public void add(BigInteger num) {
        BigInteger orig = new BigInteger(this.digits);

        digits = new byte[Math.max(orig.digits.length, num.digits.length) + 1];

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

    public void multiply(BigInteger num) {
        BigInteger orig = new BigInteger(this.digits); // I use this instead of plain `this` to prevent object changing

        digits = new byte[orig.digits.length + num.digits.length];

        for (int i = 0; i < num.digits.length; i++) {
            int a = num.digits[i]; // gets digits of the bottom number R->L
            for (int j = 0; j < orig.digits.length; j++) {
                int res = a * orig.digits[j]; //multiplies bottom number digit by thr upper number

                digits[i + j] += res % 10; // sums the last digit to the appropriate position
                if (digits[i + j] >= 10) { // carry over
                    digits[i + j + 1] += digits[i + j] / 10;
                    digits[i + j] %= 10;
                }

                digits[i + j + 1] += res / 10; // sums the first digit to the appropriate position
                if (digits[i + j + 1] >= 10) { // carry over
                    digits[i + j + 2] += digits[i + j + 1] / 10;
                    digits[i + j + 1] %= 10;
                }
            }
        }

        cutOffZeros();
    }


    public static BigInteger sum(BigInteger n1, BigInteger n2) {
        BigInteger result = new BigInteger(n1.digits);
        result.add(n2);
        return result;
    }

    public static BigInteger product(BigInteger n1, BigInteger n2) {
        BigInteger result = new BigInteger(n1.digits);
        result.multiply(n2);
        return result;
    }


    private void cutOffZeros() {
        int zeroSince = -1;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == 0 && zeroSince == -1) {
                zeroSince = i;
            } else if (digits[i] != 0 && zeroSince != -1) {
                zeroSince = -1;
            }
        }

        if (zeroSince != -1) {
            digits = Arrays.copyOf(digits, zeroSince);
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

    @Override
    public int compareTo(BigInteger other) {
        if (this.digits.length > other.digits.length) {
            return 1;
        } else if (this.digits.length < other.digits.length) {
            return -1;
        } else {
            for (int i = this.digits.length - 1; i >= 0; i--) {
                if (this.digits[i] > other.digits[i]) {
                    return 1;
                } else if (this.digits[i] < other.digits[i]) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
