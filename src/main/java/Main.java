import util.Reader;

public class Main {
    public static void main (String[] args) {
        Reader reader = new Reader("src/main/resources/dico.txt");
        HashTable table = new HashTable(reader.read(), 3);

        RepeatedSet set = new RepeatedSet("humidifierons");
        TwoSum sum = new TwoSum(set.getSet());
        RepeatedSet[] result = sum.findCompatibleWord(table);
        if (result != null)
            System.out.println("somme '" + set.getWord() + "' composée de '" + result[0].getWord() + "' et '" + result[1].getWord() + "'");
        else
            System.out.println("Impossible de décomposée la somme : " + set.getWord());
    }
}
