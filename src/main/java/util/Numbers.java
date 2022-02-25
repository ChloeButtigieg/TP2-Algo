package util;

public class Numbers {
    public static int findUpperPrimeNumber(int number) {
        int primeNumber;
        for(int index = 0;;index++) {
            primeNumber = number + index;
            if (isPrimeNumber(primeNumber)) return primeNumber;
        }
    }

    public static boolean isPrimeNumber(int number) {
        return true;
    }
}
