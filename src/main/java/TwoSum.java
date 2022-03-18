import util.ArraysUtil;
import java.util.Arrays;

public class TwoSum {
    private final char[] wordSum;
    private final int sumLength;

    public TwoSum(final char[] sum) {
        this.sumLength = sum.length;
        this.wordSum = sum;
        Arrays.sort(this.wordSum);
    }

    public RepeatedSet[] findSumDecomposition(final HashTable table) {
        final RepeatedSet[] words = new RepeatedSet[2];

        for (int index = 0; index < table.getSize(); index++) {
            for (RepeatedSet repeatedSet : table.getTable().get(index)) {

                if (ArraysUtil.ARRAYS_UTIL.containsArray(this.wordSum, repeatedSet.getSet())) {
                    RepeatedSet compatibleWord = table.findElement(compatible(repeatedSet.getSet()));

                    if (compatibleWord != null) {
                        words[0] = repeatedSet;
                        words[1] = compatibleWord;
                        return words;
                    }
                }
            }
        }
        return null;
    }

    public char[] compatible(final char[] sortedWord) {
        final int wordLength = sortedWord.length;
        if (this.sumLength <= sortedWord.length) return null;

        final char[] compatibleWord = new char[this.sumLength - wordLength];
        int compatibleWordIndex = 0;
        int sortedWordIndex = 0;

        for (int wordSumIndex = 0; wordSumIndex < this.sumLength; wordSumIndex++) {
            if ( sortedWordIndex < wordLength && sortedWord[sortedWordIndex] == this.wordSum[wordSumIndex])
                sortedWordIndex++;
            else {
                compatibleWord[compatibleWordIndex] = this.wordSum[wordSumIndex];
                compatibleWordIndex++;
            }
        }
        return compatibleWord;
    }
}
