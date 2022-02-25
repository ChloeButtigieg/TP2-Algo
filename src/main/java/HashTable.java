import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {
    private ArrayList<LinkedList<RepeatedSet>> table;
    private final int filling;
    private final int size;

    public HashTable(ArrayList<RepeatedSet> repeatedSets, final int filling) {
        this.filling = filling;
        this.size = computeSize(repeatedSets.size());
        initTable();
    }

    private void initTable() {
        this.table = new ArrayList<>();
        for (int index = 0; index < size; index++) {
            LinkedList<RepeatedSet> list = table.get(index);
            list = new LinkedList<>();
        }
    }

    private int computeSize(final int nbOfElement) {
        final int rawSize = (int) Math.ceil(nbOfElement / this.filling);
        return 0;
    }
}
