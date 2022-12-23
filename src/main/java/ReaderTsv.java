import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReaderTsv {
    private HashMap<String, String> categoryItems = new HashMap<>();

    public ReaderTsv() {
        this.categoryItems = categoryItems;
    }

    public HashMap<String, String> run(File tsvFile) {

        try (
                BufferedReader br = new BufferedReader(new FileReader(tsvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                categoryItems.put(parts[0], parts[1]);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return categoryItems;
    }
}
