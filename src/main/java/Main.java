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

        //displayRenduTP2();

        //displayPossibleSums(miniDicTable, "src/main/possible-sums/possible-sums-for-mini-dic.txt", readerMiniDic.read());
        //displayPossibleSums(dicTable, "src/main/possible-sums/possible-sums-for-dic.txt", readerDic.read());
    }

    public static void displayRenduTP2() {
        System.out.println("\n------------- RENDU TP2 -------------\n");

        /* TEST : Stockage du mini-dictionnaire */

        System.out.println("----- TEST : Stockage du mini-dictionnaire -----");
        long start = System.currentTimeMillis();

        Reader readerMiniDic = new Reader("src/main/resources/miniDico.txt");
        HashTable tableMiniDic = new HashTable(readerMiniDic.read(), 3);

        long end = System.currentTimeMillis();
        double time = (end - start) / 1000.0;
        System.out.println("Temps d'exécution : " + time + " seconde(s)\n");

        /* TEST : 2-somme de 'adeeiilnnnorux' avec le mini-dictionnaire */

        System.out.println("----- TEST : 2-somme de 'adeeiilnnnorux' avec le mini-dictionnaire -----");
        start = System.currentTimeMillis();
        System.out.println(getTwoSumDecomposition(tableMiniDic, "adeeiilnnnorux"));
        end = System.currentTimeMillis();
        time = (end - start) / 1000.0;
        System.out.println("Temps d'exécution : " + time + " seconde(s)\n");

        /* TEST : 2-somme de 'aaaaeinnorsssstwz' avec le mini-dictionnaire */

        System.out.println("----- TEST : 2-somme de 'aaaaeinnorsssstwz' avec le mini-dictionnaire -----");
        start = System.currentTimeMillis();
        System.out.println(getTwoSumDecomposition(tableMiniDic, "aaaaeinnorsssstwz"));
        end = System.currentTimeMillis();
        time = (end - start) / 1000.0;
        System.out.println("Temps d'exécution : " + time + " seconde(s)\n");

        /* TEST : Stockage du dictionnaire */

        System.out.println("----- TEST : Stockage du dictionnaire -----");
        start = System.currentTimeMillis();

        Reader readerDic = new Reader("src/main/resources/dico.txt");
        HashTable tableDic = new HashTable(readerDic.read(), 3);

        end = System.currentTimeMillis();
        time = (end - start) / 1000.0;
        System.out.println("Temps d'exécution : " + time + " seconde(s)\n");

        /* TEST : 2-somme de 'abeeiiillnnorrsstyzé' avec le dictionnaire */

        System.out.println("----- TEST : 2-somme de 'abeeiiillnnorrsstyzé' avec le dictionnaire -----");
        start = System.currentTimeMillis();
        System.out.println(getTwoSumDecomposition(tableDic, "abeeiiillnnorrsstyzé"));
        end = System.currentTimeMillis();
        time = (end - start) / 1000.0;
        System.out.println("Temps d'exécution : " + time + " seconde(s)\n");

        /* TEST : 2-somme de 'aaabdeiiillnorrrstzz' avec le dictionnaire */

        System.out.println("----- TEST : 2-somme de 'aaabdeiiillnorrrstzz' avec le dictionnaire -----");
        start = System.currentTimeMillis();
        System.out.println(getTwoSumDecomposition(tableDic, "aaabdeiiillnorrrstzz"));
        end = System.currentTimeMillis();
        time = (end - start) / 1000.0;
        System.out.println("Temps d'exécution : " + time + " seconde(s)\n");

        /* TEST : 2-somme de 'aadehimnnoorrttxz' avec le dictionnaire */

        System.out.println("----- TEST : 2-somme de 'aadehimnnoorrttxz' avec le dictionnaire -----");
        start = System.currentTimeMillis();
        System.out.println(getTwoSumDecomposition(tableDic, "aadehimnnoorrttxz"));
        end = System.currentTimeMillis();
        time = (end - start) / 1000.0;
        System.out.println("Temps d'exécution : " + time + " seconde(s)\n");

        /* TEST : 2-somme de 'buttigiegchloe' pour Buttigieg Chloe avec le dictionnaire */

        System.out.println("----- TEST : 2-somme de 'buttigiegchloe' avec le dictionnaire -----");
        start = System.currentTimeMillis();
        System.out.println(getTwoSumDecomposition(tableDic, "buttigiegchloe"));
        end = System.currentTimeMillis();
        time = (end - start) / 1000.0;
        System.out.println("Temps d'exécution : " + time + " seconde(s)\n");
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
                    RepeatedSet[] decomposition = sum.findSumDecomposition(table);

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
        RepeatedSet[] result = sum.findSumDecomposition(table);

        if (result != null)
            return "'" + wordSet.getWord() + "' est composé de '" + result[0].getWord() + "' et de '" + result[1].getWord() + "'.";
        else
            return "Impossible de décomposer la somme : '" + wordSet.getWord() + "'.";
    }
}
