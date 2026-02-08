package org.example.tcp_udp_portscanner;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    void ClearBtnClicked(MouseEvent event) {

    }

    @FXML
    void DeleteBtnClicked(MouseEvent event) {

    }

    @FXML
    void RunScanBtnClicked(MouseEvent event) {
        String target = "localhost";
        int port = 80;

        boolean open = scanner.isPortOpen(target,port,200);

        ScanResult result = new ScanResult(
                scanResults.size() + 1,
                target,
                port,
                "TCP",
                open ? "OPEN" : "CLOSED",
                java.time.LocalDateTime.now().toString()

        );
        scanResults.add(result);
    }

    @FXML
    void UpdateBtnClicked(MouseEvent event) {

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
    }


}
