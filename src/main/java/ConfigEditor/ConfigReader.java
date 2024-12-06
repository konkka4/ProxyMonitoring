package ConfigEditor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
    public static String getStringConfig(String path) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (!firstLine) {
                    content.append(System.lineSeparator());
                }
                content.append(line);
                firstLine = false;
            }
        } catch (IOException e) {
            System.err.println("Error while reading: " + e.getMessage());
        }
        return content.toString();
    }
}
