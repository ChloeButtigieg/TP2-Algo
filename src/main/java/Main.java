import util.Reader;

import java.util.Arrays;

public class Main {
    public static void main (String[] args) {
        Reader reader = new Reader("src/main/resources/dico.txt");
        System.out.println(reader.read());
    }
}
