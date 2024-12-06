package ConfigEditor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigWriter {
    public static void updateConfig(String path, String configurations) {
        if(configurations.isEmpty()) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            writer.write(configurations); // Очищає файл і записує нові дані
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }
}
