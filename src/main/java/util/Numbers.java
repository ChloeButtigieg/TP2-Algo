package util;

import java.util.ArrayList;
import java.util.Arrays;

public class Numbers {
    public final static Numbers NUMBERS_UTIL = new Numbers();

    public int findUpperPrimeNumber(final int number) {
        final ArrayList<Integer> primesNumber = getPrimesNumber(number);
        for(int primeNumber = number;;primeNumber++) {
            if (isPrimeNumber(primeNumber, primesNumber)) return primeNumber;
        }
    }

    public boolean isPrimeNumber(final int number, final ArrayList<Integer> primeNumbers) {
        final int squareRoot = (int) Math.floor(Math.sqrt(number));
        for (int index = 0; index < primeNumbers.size() && primeNumbers.get(index) <= squareRoot; index++) {
            if (number % primeNumbers.get(index) == 0) return false;
        }
        return true;
    }

    public ArrayList<Integer> getPrimesNumber(final int max) {
        int actual = 2;
        final ArrayList<Integer> primeNumbers = new ArrayList<>();

        while (actual <= max) {
            if (isPrimeNumber(actual, primeNumbers)) primeNumbers.add(actual);
            actual++;
        }
        return primeNumbers;
    }
}
