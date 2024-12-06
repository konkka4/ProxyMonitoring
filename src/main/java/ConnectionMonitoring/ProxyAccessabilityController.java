package ConnectionMonitoring;

import model.ProxyDetails;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import model.ProxyDetails.*;

public class ProxyAccessabilityController {

    public boolean isAvaible(ProxyDetails proxy) {
        try (Socket socket = new Socket()) {
            SocketAddress socketAddress = new InetSocketAddress(proxy.getIp(), Integer.parseInt(proxy.getPort()));

            socket.connect(socketAddress, 2000);

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void updateIfNotAvaible(ProxyDetails proxy)
    {
        String port = proxy.getPort();
        int portInt = Integer.parseInt(port);
        System.out.println(String.valueOf(portInt+1));
        proxy.setPort(String.valueOf(portInt+1));
    }

    public void checkConnection(ProxyDetails proxy)
    {
        if(!isAvaible(proxy)) updateIfNotAvaible(proxy);
    }
}
