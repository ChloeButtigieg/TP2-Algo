package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    private final String filePath;

    public Reader(final String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> read() {
        ArrayList<String> array = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filePath))) {

            String line = bufferedReader.readLine();
            while (line != null) {
                array.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }
}
