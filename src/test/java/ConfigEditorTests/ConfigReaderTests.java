package ConfigEditorTests;

import ConfigEditor.ConfigReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ConfigReaderTests {


    @Test
    void testGetStringConfig() throws IOException {
        String testFileContent = "Line 1\nLine 2\nLine 3";
        Path tempFile = Files.createTempFile("testConfig", ".txt");
        Files.writeString(tempFile, testFileContent);

        String result = ConfigReader.getStringConfig(tempFile.toString());

        System.out.println("Expected content length: " + testFileContent.length());
        System.out.println("Actual content length: " + result.length());
        System.out.println("Expected content: " + testFileContent);
        System.out.println("Actual content: " + result);

        assertEquals(testFileContent, result, "Рядки мають співпадати");

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testGetStringConfig_EmptyFile() throws IOException {
        Path tempFile = Files.createTempFile("emptyConfig", ".txt");

        String result = ConfigReader.getStringConfig(tempFile.toString());

        assertTrue(result.isEmpty(), "У разі пустого файлу має бути пустий рядок");

    }

    @Test
    void testGetStringConfig_ContentDoesNotMatch() throws IOException {
        // Arrange
        String expectedContent = "Expected Line 1\nExpected Line 2\nExpected Line 3";
        String actualFileContent = "Actual Line 1\nActual Line 2\nActual Line 3";
        Path tempFile = Files.createTempFile("mismatchConfig", ".txt");
        Files.writeString(tempFile, actualFileContent);

        String result = ConfigReader.getStringConfig(tempFile.toString());

        assertNotEquals(expectedContent, result.trim(), "Рядки мають не співпадати");

    }

    @Test
    void testGetStringConfig_wrongPath() throws IOException {
        String wrongPath = "invalid/path/to/file.txt";

        String result = ConfigReader.getStringConfig(wrongPath);

        assertEquals("", result, "Результат повинен бути порожнім рядком для неправильного шляху");
    }


}