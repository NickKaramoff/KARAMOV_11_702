package ru.karamoff;

import java.util.Arrays;

public class BigInteger implements Comparable<BigInteger> {

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

    /**
     * Adds another number to the current one.
     *
     * @param num Number to be added
     */
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

    /**
     * Multiplies current number by another one.
     *
     * @param num Number to be multiplied by
     */
    public void multiply(BigInteger num) {
        BigInteger bigger, smaller;

        if (this.digits.length >= num.digits.length) {
            bigger = new BigInteger(this.digits); // I use this instead of plain `this` to prevent object changing
            smaller = num;
        } else {
            smaller = new BigInteger(this.digits);
            bigger = num;
        }

        digits = new int[bigger.digits.length + smaller.digits.length];

        for (int i = 0; i < smaller.digits.length; i++) {
            int a = smaller.digits[i];
            for (int j = 0; j < bigger.digits.length; j++) {
                int res = a * bigger.digits[j];

                digits[i + j] += res % 10;
                if (digits[i + j] >= 10) {
                    digits[i + j + 1] += digits[i + j] / 10;
                    digits[i + j] %= 10;
                }

                digits[i + j + 1] += res / 10;
                if (digits[i + j + 1] >= 10) {
                    digits[i + j + 2] += digits[i + j + 1] / 10;
                    digits[i + j + 1] %= 10;
                }
            }
        }

        cutOffZeros();
    }

    /**
     * Sums two numbers and returns the result
     *
     * @param n1 First number
     * @param n2 Second number
     * @return The result of addition
     */
    public static BigInteger sum(BigInteger n1, BigInteger n2) {
        BigInteger result = new BigInteger(n1.digits);
        result.add(n2);
        return result;
    }

    /**
     * Multiplies two numbers by each other and returns the result
     *
     * @param n1 First number
     * @param n2 Second number
     * @return The result of multiplication
     */
    public static BigInteger product(BigInteger n1, BigInteger n2) {
        BigInteger result = new BigInteger(n1.digits);
        result.multiply(n2);
        return result;
    }

    /**
     * Finds redundant zeros at the beginning of the number and deletes them
     */
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
