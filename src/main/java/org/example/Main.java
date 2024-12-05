package org.example;

import ConnectionMonitoring.ProxyStatusController;
import ConnectionMonitoring.ProxyStatusController.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(ProxyStatusController.isHostReachable("8.8.8.8"));
    }
}