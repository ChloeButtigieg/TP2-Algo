package util;

public class ArraysUtil {
    public static final ArraysUtil ARRAYS_UTIL = new ArraysUtil();

    public boolean containsArray(final char[] array, final char[] smallerArray) {
        int arrayIndex = 0;
        int smallerArrayIndex = 0;

        while (smallerArrayIndex < smallerArray.length && arrayIndex < array.length) {
            if (array[arrayIndex] == smallerArray[smallerArrayIndex]) {
                arrayIndex++;
                smallerArrayIndex++;
            } else {
                if (array[arrayIndex] > smallerArray[smallerArrayIndex]) return false;
                arrayIndex++;
            }
        }

        if (smallerArrayIndex == smallerArray.length) return true;
        else return false;
    }
}
