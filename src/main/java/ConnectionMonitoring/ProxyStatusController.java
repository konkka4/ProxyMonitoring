package ConnectionMonitoring;

import java.io.*;
import java.net.InetAddress;

public class ProxyStatusController {

    public static String getIpFromConfig(String path) {
        String ipAddress = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            ipAddress = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error while reading " + e.getMessage());
        }
        return ipAddress;
    }

    public static void updateIpToConfig(String path, String ipAddress) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(ipAddress);
        } catch (IOException e) {
            System.err.println("Error while writing" + e.getMessage());
        }
    }


    public static boolean isHostReachable(String ipAddress) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        return inetAddress.isReachable(2000);
    }
}
