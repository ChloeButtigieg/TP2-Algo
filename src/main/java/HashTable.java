import util.Numbers;

import java.util.*;

public class HashTable {
    private ArrayList<LinkedList<RepeatedSet>> table;
    private final int filling;
    private final int size;

    public HashTable(ArrayList<String> words, final int filling) {
        this.filling = filling;
        final int nbOfElement = words.size();
        this.size = computeSize(nbOfElement);
        initTable();

        for (String word : words) {
            RepeatedSet wordSet = new RepeatedSet(word);
            if (!contains(wordSet)) {
                int keyIndex = getKeyIndex(wordSet.getSet());
                this.table.get(keyIndex).add(wordSet);
            }
        }
    }

    public int getSize() { return this.size; }

    public ArrayList<LinkedList<RepeatedSet>> getTable() { return this.table; }

    private void initTable() {
        this.table = new ArrayList<>();
        for (int index = 0; index < this.size; index++) {
            this.table.add(new LinkedList<>());
        }
    }

    private int computeSize(final int nbOfElement) {
        final int rawSize = nbOfElement / this.filling;
        return Numbers.NUMBERS_UTIL.findUpperPrimeNumber(rawSize);
    }

    public RepeatedSet findElement(final char[] setToFind) {
        if (setToFind == null) return null;

        final int key = getKeyIndex(setToFind);
        for (RepeatedSet repeatedSet : this.table.get(key)) {
            if (Arrays.equals(setToFind, repeatedSet.getSet())) return repeatedSet;
        }
        return null;
    }

    public boolean contains(RepeatedSet repeatedSet) {
        if (findElement(repeatedSet.getSet()) != null) return true;
        return false;
    }

    private int getKeyIndex(final char[] wordSet) {
        final int length = wordSet.length;
        int keyIndex = 0;

        for (int index = 0; index < length; index++) {
            int ascii = wordSet[index];
            double pow = Math.pow(256, length-1-index);
            keyIndex = (int) (keyIndex +  ((ascii * pow) % this.size));
        }
        return keyIndex % this.size;
    }

    @Override
    public String toString() {
        final StringBuilder display = new StringBuilder();
        for (LinkedList<RepeatedSet> list : this.table) {
            display.append(list.toString()).append("\n");
        }
        return display.toString();
    }

    public Map<Integer, Integer> computeNbOfElementWithSameKey() {
        final Map<Integer, Integer> count = new HashMap<>();
        for (LinkedList<RepeatedSet> keyList : this.table) {
            Integer nbOfElementWithSameKey = keyList.size();

            if (!count.containsKey(nbOfElementWithSameKey))
                count.put(nbOfElementWithSameKey, 1);
            else
                count.replace(nbOfElementWithSameKey, count.get(nbOfElementWithSameKey)+1);
        }
        return count;
    }
}
