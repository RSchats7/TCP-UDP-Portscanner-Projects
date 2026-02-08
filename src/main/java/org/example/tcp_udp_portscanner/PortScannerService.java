package org.example.tcp_udp_portscanner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class PortScannerService {

    public boolean isPortOpen (String host,int Port, int TimeoutMS) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, Port), TimeoutMS);
            return true;
        } catch (IOException e) {
                return false;
        }

    }
}
