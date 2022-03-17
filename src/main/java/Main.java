import util.Reader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main (String[] args) {
        Reader readerMiniDico = new Reader("src/main/resources/miniDico.txt");
        Reader readerDico = new Reader("src/main/resources/dico.txt");

        HashTable miniDicoTable = new HashTable(readerMiniDico.read(), 3);
        HashTable dicoTable = new HashTable(readerDico.read(), 3);

        displaySums(miniDicoTable, dicoTable);

        //displayPossibleSums(miniDicoTable, "src/main/possible-sums/possible-sums-for-mini-dico.txt");
        //displayPossibleSums(dicoTable, "src/main/possible-sums/possible-sums-for-dico.txt");
    }

    public static void displaySums(final HashTable miniDicoTable, final HashTable dicoTable) {
        System.out.println("\n------------- MINI-DICO -------------\n");
        System.out.println(
                getTwoSumDecomposition(miniDicoTable, "") + "\n" +
                        getTwoSumDecomposition(miniDicoTable, "ADN") + "\n" +
                        getTwoSumDecomposition(miniDicoTable, "immunisé") + "\n\n" +
                        getTwoSumDecomposition(miniDicoTable, "èHcaawaïcs")
        );

        System.out.println("\n------------- DICO -------------\n");
        System.out.println(
                getTwoSumDecomposition(dicoTable, "") + "\n" +
                        getTwoSumDecomposition(dicoTable, "ADN") + "\n\n" +
                        getTwoSumDecomposition(dicoTable, "immunisé") + "\n" +
                        getTwoSumDecomposition(dicoTable, "humidifierons")
        );
        System.out.println("\n-------------------------------\n");
    }

    public static void displayPossibleSums(final HashTable table, final String filePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {

            for (int index = 0; index < table.getSize(); index++) {
                for (RepeatedSet word : table.getTable().get(index)) {
                    TwoSum sum = new TwoSum(word.getSet());
                    RepeatedSet[] decomposition = sum.findCompatibleWord(table);

                    if (decomposition != null) {
                        bufferedWriter.write(word.getWord() + " = " +
                                decomposition[0].getWord() + ", " +
                                decomposition[1].getWord()
                        );
                        bufferedWriter.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
