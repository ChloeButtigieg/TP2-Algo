import java.util.Arrays;

public class RepeatedSet {
    private final String word;
    private final char[] set;

    public RepeatedSet(final String word) {
        this.word = word;
        this.set = word.toCharArray();
        Arrays.sort(this.set);
    }
}
