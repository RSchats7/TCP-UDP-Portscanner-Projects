package org.example.tcp_udp_portscanner;

import java.io.IOException;
import java.net.*;

public class PortScannerService {

    public boolean isPortOpen (String host,int Port, int TimeoutMS) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, Port), TimeoutMS);
            return true;
        } catch (IOException e) {
                return false;
        }

    }
    public boolean isUdpPortOpen(String host, int port, int timeout) {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(timeout);

            InetAddress address = InetAddress.getByName(host);

            byte[] buffer = new byte[1];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);

            socket.send(packet);

            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            socket.receive(response);

            return true;
        } catch (SocketTimeoutException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
