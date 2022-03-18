import java.util.Arrays;

public class RepeatedSet {
    private final String word;
    private final char[] set;

    public RepeatedSet(final String word) {
        this.word = word;
        this.set = word.toCharArray();
        Arrays.sort(this.set);
    }

    public char[] getSet() {
        return this.set;
    }

    public String getWord() { return this.word; }

    @Override
    public String toString() {
        return "{'" + word + '\'' + ", " + Arrays.toString(set) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepeatedSet that = (RepeatedSet) o;
        return Arrays.equals(set, that.set);
    }
}
