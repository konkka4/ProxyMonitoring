package ConnectionMonitoringTests;

import ConnectionMonitoring.ProxyStatusController;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ProxyStatusControllerTests {
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("test-config", ".txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testGetIpFromConfig() throws IOException {
        String expectedIp = "192.168.0.1";
        Files.writeString(tempFile, expectedIp);

        String actualIp = ProxyStatusController.getIpFromConfig(tempFile.toString());
        assertEquals(expectedIp, actualIp, "Записаний IP співпадає");
    }

    @Test
    void testUpdateIpToConfig() throws IOException {
        String newIp = "10.0.0.1";
        ProxyStatusController.updateIpToConfig(tempFile.toString(), newIp);

        String fileContent = Files.readString(tempFile);
        assertEquals(newIp, fileContent, "Записаний IP співпадає");
    }

    @Test
    void testIsHostReachable() throws IOException {
        // Проверяем доступность заведомо доступного адреса
        assertTrue(ProxyStatusController.isHostReachable("8.8.8.8"), "Хост має бути доступний");
    }

    @Test
    void testNotHostReachable() throws IOException {
        assertFalse(ProxyStatusController.isHostReachable("192.168.110.110"), "Хост має бути недоступний");
    }

    @Test
    void testGetIpFromConfigWhenFileIsEmpty() throws IOException {
        Files.writeString(tempFile, "");

        String actualIp = ProxyStatusController.getIpFromConfig(tempFile.toString());
        assertNull(actualIp, "Якщо файл пустий, має повернутися null");
    }

    @Test
    void testUpdateIpToConfigWhenFileDoesNotExist() {
        String newIp = "192.168.0.1";
        Path nonExistentFile = tempFile.resolveSibling("nonexistent.txt");

        ProxyStatusController.updateIpToConfig(nonExistentFile.toString(), newIp);

        assertTrue(Files.exists(nonExistentFile), "Файл має бути створений");
        try {
            String fileContent = Files.readString(nonExistentFile);
            assertEquals(newIp, fileContent, "Вміст файла вмає співпадати");
        } catch (IOException e) {
            fail("Помилка читаня файлу: " + e.getMessage());
        }
    }
}