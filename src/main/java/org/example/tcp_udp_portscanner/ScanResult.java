package org.example.tcp_udp_portscanner;

public class ScanResult {

    private final int scanId;
    private final String target;
    private final int port;
    private final String protocol;
    private final String status;
    private final String date;

    public ScanResult(int scanId, String target, int port,
                      String protocol, String status, String date) {
        this.scanId = scanId;
        this.target = target;
        this.port = port;
        this.protocol = protocol;
        this.status = status;
        this.date = date;
    }

    public int getScanId() {
        return scanId;
    }

    public String getTarget() {
        return target;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
}
