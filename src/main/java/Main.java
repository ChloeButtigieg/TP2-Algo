import util.Reader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {
        Reader readerMiniDic = new Reader("src/main/resources/miniDico.txt");
        Reader readerDic = new Reader("src/main/resources/dico.txt");

        HashTable miniDicTable = new HashTable(readerMiniDic.read(), 3);
        HashTable dicTable = new HashTable(readerDic.read(), 3);

        displaySums(miniDicTable, dicTable);
        displayTableComposition(miniDicTable, dicTable);

        //displayPossibleSums(miniDicTable, "src/main/possible-sums/possible-sums-for-mini-dic.txt", readerMiniDic.read());
        //displayPossibleSums(dicTable, "src/main/possible-sums/possible-sums-for-dic.txt", readerDic.read());
    }

    public static void displaySums(final HashTable miniDicTable, final HashTable dicTable) {
        System.out.println("\n------------- MINI-DICO -------------\n");
        System.out.println(
                getTwoSumDecomposition(miniDicTable, "") + "\n" +
                        getTwoSumDecomposition(miniDicTable, "ADN") + "\n" +
                        getTwoSumDecomposition(miniDicTable, "immunisé") + "\n\n" +
                        getTwoSumDecomposition(miniDicTable, "èHcaawaïcs")
        );

        System.out.println("\n------------- DICO -------------\n");
        System.out.println(
                getTwoSumDecomposition(dicTable, "") + "\n" +
                        getTwoSumDecomposition(dicTable, "ADN") + "\n\n" +
                        getTwoSumDecomposition(dicTable, "immunisé") + "\n" +
                        getTwoSumDecomposition(dicTable, "humidifierons")
        );
    }

    public static void displayPossibleSums(final HashTable table, final String filePath, final ArrayList<String> data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {

                for (String word : data) {
                    RepeatedSet wordSet = new RepeatedSet(word);
                    TwoSum sum = new TwoSum(wordSet.getSet());
                    RepeatedSet[] decomposition = sum.findCompatibleWord(table);

                    if (decomposition != null) {
                        bufferedWriter.write(word + " = " +
                                decomposition[0].getWord() + ", " +
                                decomposition[1].getWord()
                        );
                        bufferedWriter.newLine();
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayTableComposition(final HashTable miniDicTable, final HashTable dicTable) {
        System.out.println("\n--------- COMPOSITION ---------\n");
        System.out.println("{Number of element = Number of key with the same quantity of associated element}");
        System.out.println("MINI-DICO : " + miniDicTable.computeNbOfElementWithSameKey());
        System.out.println("DICO : " + dicTable.computeNbOfElementWithSameKey());
        System.out.println("\n-------------------------------\n");
    }

    public static String getTwoSumDecomposition(final HashTable table, final String word) {
        RepeatedSet wordSet = new RepeatedSet(word);
        TwoSum sum = new TwoSum(wordSet.getSet());
        RepeatedSet[] result = sum.findCompatibleWord(table);

        if (result != null)
            return "'" + wordSet.getWord() + "' est composé de '" + result[0].getWord() + "' et de '" + result[1].getWord() + "'.";
        else
            return "Impossible de décomposer la somme : '" + wordSet.getWord() + "'.";
    }
}
