package org.example.tcp_udp_portscanner;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class HelloController {

    private final PortScannerService scanner = new PortScannerService();
    private final ObservableList<ScanResult> scanResults =
            FXCollections.observableArrayList();
    @FXML
    private Button ClearBtn;

    @FXML
    private TableColumn<ScanResult,String> Date;

    @FXML
    private Button DeleteBtn;

    @FXML
    private Label MenuLbl;

    @FXML
    private TableColumn<ScanResult, Integer> Port;

    @FXML
    private TableColumn<ScanResult, String> Protocol;

    @FXML
    private Button RunScanBtn;

    @FXML
    private TableColumn<ScanResult, Integer> ScanId;

    @FXML
    private TableColumn<ScanResult, String> Status;

    @FXML
    private TableView<ScanResult> TableView;

    @FXML
    private TableColumn<ScanResult, String> Target;

    @FXML
    private Button UpdateBtn;

    @FXML
    private TextField portField;

    @FXML
    private TextField targetField;

    @FXML
    void ClearBtnClicked(MouseEvent event) {

    }

    @FXML
    void DeleteBtnClicked(MouseEvent event) {

    }

    @FXML
    void RunScanBtnClicked(MouseEvent event) {

        String target = targetField.getText();

        if (target.isEmpty() || portField.getText().isEmpty()) {
            System.out.println("Fields cannot be empty");
            return;
        }

        int port;

        try {
            port = Integer.parseInt(portField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid port number");
            return;
        }

        if (port < 1 || port > 65535) {
            System.out.println("Invalid port range");
            return;
        }

        String protocol = protocolBox.getValue();
        boolean open;

        if (protocol.equals("TCP")) {
            open = scanner.isPortOpen(target, port, 200);
        } else {
            open = scanner.isUdpPortOpen(target, port, 200);
        }

        ScanResult result = new ScanResult(
                scanResults.size() + 1,
                target,
                port,
                protocol,
                protocol.equals("UDP")
                        ? (open ? "OPEN" : "OPEN|FILTERED")
                        : (open ? "OPEN" : "CLOSED"),
                java.time.LocalDateTime.now().toString()
        );

        scanResults.add(result);
    }

    @FXML
    void UpdateBtnClicked(MouseEvent event) {
        exportToCSV(scanResults);
    }


    @FXML
    public void initialize() {
        TableView.setItems(scanResults);

        ScanId.setCellValueFactory(new PropertyValueFactory<>("scanId"));
        Target.setCellValueFactory(new PropertyValueFactory<>("target"));
        Port.setCellValueFactory(new PropertyValueFactory<>("port"));
        Protocol.setCellValueFactory(new PropertyValueFactory<>("protocol"));
        Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        protocolBox.getItems().addAll("TCP", "UDP");
        protocolBox.setValue("TCP"); // default
    }
    @FXML
    private ComboBox<String> protocolBox;

    public void exportToCSV(ObservableList<ScanResult> results) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter("scan_results.csv")) {

            writer.println("ScanID,Target,Port,Protocol,Status,Date");

            for (ScanResult r : results) {
                writer.println(
                        r.getScanId() + "," +
                                r.getTarget() + "," +
                                r.getPort() + "," +
                                r.getProtocol() + "," +
                                r.getStatus() + "," +
                                r.getDate()
                );
            }

            System.out.println("Exported to scan_results.csv");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
