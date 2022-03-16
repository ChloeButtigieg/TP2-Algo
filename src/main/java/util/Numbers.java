package util;

import java.util.ArrayList;
import java.util.Arrays;

public class Numbers {
    public final static Numbers NUMBERS_UTIL = new Numbers();

    public int findUpperPrimeNumber(final int number) {
        final int squareRoot = (int) Math.floor(Math.sqrt(number));
        final ArrayList<Integer> primesNumber = getPrimesNumber(squareRoot, 0, new ArrayList<>());
        for(int primeNumber = number;;primeNumber++) {
            if (isPrimeNumber(primeNumber, primesNumber)) return primeNumber;
        }
    }

    public boolean isPrimeNumber(final int number, final ArrayList<Integer> primeNumbers) {
        final int squareRoot = (int) Math.floor(Math.sqrt(number));
        System.out.println(squareRoot);
        for (int index = 0; index < primeNumbers.size() && primeNumbers.get(index) <= squareRoot; index++) {
            if (number % primeNumbers.get(index) == 0) return false;
        }
        return true;
    }

    public ArrayList<Integer> getPrimesNumber(final int max, final int actual, final ArrayList<Integer> primeNumbers) {
        if (actual > max) return primeNumbers;

        if (primeNumbers.size() == 0 && max >= 2)
            return getPrimesNumber(max, 3, new ArrayList<>(Arrays.asList(2)));

        if (isPrimeNumber(actual, primeNumbers)) primeNumbers.add(actual);
        return getPrimesNumber(max, actual+1, primeNumbers);
    }
}
