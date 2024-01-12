package lk.ijse.plantLife.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.plantLife.Impl.InventoryDAOImpl;
import lk.ijse.plantLife.bo.Impl.InventoryBOImpl;
import lk.ijse.plantLife.dto.InventoryDto;
import lk.ijse.plantLife.dto.tm.InventoryTable;
import lk.ijse.plantLife.model.InventoryModel;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class InventoryFromController {

    @FXML
    private AnchorPane root;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<?, ?> clmGreenHouse;

    @FXML
    private TableColumn<?, ?> clmInventoryId;

    @FXML
    private TableColumn<?, ?> clmPlantId;

    @FXML
    private TableView<InventoryTable> tblInventory;

    @FXML
    private TextField txtGreenHouse;

    @FXML
    private TextField txtInventoryId;

    @FXML
    private TextField txtPlantId;

    private static InventoryDto inventoryDto = new InventoryDto();
   // InventoryDAOImpl inventoryDAO = new InventoryDAOImpl();
    InventoryBOImpl inventoryBO = new InventoryBOImpl();

    public void initialize() {
        setCellValueFactory();
        loadAllInventory();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        String inventoryId = txtInventoryId.getText();
        String plantId = txtPlantId.getText();
        String greenHouse = txtGreenHouse.getText();

        inventoryDto = new InventoryDto(inventoryId, plantId, greenHouse);

        try {
            boolean isSaved = inventoryBO.saveInventory(inventoryDto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"To Inventory Saved").show();
                //clearFields();
                loadAllInventory();
                setCellValueFactory();
            }
        } catch (Exception e) {
            //throw new RuntimeException(e);
            new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
        }
    }

    private void loadAllInventory() {
        var model = new InventoryDAOImpl();

        ObservableList<InventoryTable> obList = FXCollections.observableArrayList();

        try {
            List<InventoryDto> dtoList = model.getAllInventory();

            for (InventoryDto dto : dtoList) {
                obList.add(
                        new InventoryTable(
                                dto.getInventoryId(),
                                dto.getPlantId(),
                                dto.getGreenHouse()
                        )
                );
            }
            System.out.println("loadInvo");

            tblInventory.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {

        }
    }
    private void setCellValueFactory() {
        clmInventoryId.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        clmPlantId.setCellValueFactory(new PropertyValueFactory<>("plantId"));
        clmGreenHouse.setCellValueFactory(new PropertyValueFactory<>("greenhouse"));
        //System.out.println("settable");
    }

    private void clearFields() {
        txtInventoryId.setText("");
        txtPlantId.setText("");
        txtGreenHouse.setText("");

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dash Board");
        stage.centerOnScreen();
    }

}
