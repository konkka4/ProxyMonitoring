package ConfigEditorTests;

import ConfigEditor.ConfigWriter;
import ConfigEditor.ConfigWriter.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigWriterTests {

    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("testConfig", ".txt");

        String initialConfig = "ip=localhost\nport=8000";
        Files.write(tempFile, initialConfig.getBytes());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }
    @Test
    void testUpdateConfig_Success() throws IOException {

        String config = "ip=localhost\nport=8001";
        ConfigWriter.updateConfig(tempFile.toString(), config);

        String fileContent = new String(Files.readAllBytes(tempFile));
        assertEquals(config, fileContent, "Файл записано правильно");
    }

    @Test
    void testUpdateConfig_EmptyConfigurations() throws IOException {
        String config = "";
        String existingConfig = new String(Files.readAllBytes(tempFile));
        ConfigWriter.updateConfig(tempFile.toString(), config);

        String fileContent = new String(Files.readAllBytes(tempFile));
        assertEquals(existingConfig, fileContent, "Файл конфігурації не мав змінитися");
    }

    @Test
    void testUpdateConfig_InvalidPath() {
        String invalidPath = "invalid/path/file.txt";
        String config = "key=value";

        assertDoesNotThrow(() -> ConfigWriter.updateConfig(invalidPath, config), "Метод має не кидати виключення при неправильному шляху");
    }
}
