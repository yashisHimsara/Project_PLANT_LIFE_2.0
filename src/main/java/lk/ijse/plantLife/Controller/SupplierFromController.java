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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.plantLife.Impl.SupplierDAOImpl;
import lk.ijse.plantLife.dto.CustomerDto;
import lk.ijse.plantLife.dto.SupplierDto;
import lk.ijse.plantLife.dto.tm.CustomerTable;
import lk.ijse.plantLife.dto.tm.SupplierTable;
import lk.ijse.plantLife.model.CustomerModel;
import lk.ijse.plantLife.model.SupplierModel;
import lk.ijse.plantLife.utill.Regex;
import lk.ijse.plantLife.utill.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SupplierFromController {
    public AnchorPane root;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtplantCount;

    @FXML
    private TextField txtplantName;

    @FXML
    private TextField txtprice;

    @FXML
    private TextField txtsuplierId;

    @FXML
    private TextField txtplantId;

    @FXML
    private TableColumn<?, ?> clmPlantCount;

    @FXML
    private TableColumn<?, ?> clmPlantName;

    @FXML
    private TableColumn<?, ?> clmPrice;

    @FXML
    private TableColumn<?, ?> clmSupplierId;

    @FXML
    private TableColumn<?, ?> clmPlantId;

    @FXML
    private TableView<SupplierTable> tblSupplier;


    private static SupplierDto supplierDto = new SupplierDto();
    SupplierDAOImpl supplierDAO = new SupplierDAOImpl();

    public void initialize() {
        setCellValueFactory();
        loadAllSupplier();
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String supplierId = txtsuplierId.getText();
        String plantName = txtplantName.getText();
        String plantId = txtplantId.getText();
        int plantCount = Integer.parseInt(txtplantCount.getText());
        double price = Double.parseDouble(txtprice.getText());

        supplierDto = new SupplierDto(supplierId,plantName, plantId, plantCount, price);

        try {
            boolean isSaved = supplierDAO.SaveSupplier(supplierDto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier is Saved").show();
                loadAllSupplier();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private void loadAllSupplier() {
        //var model = new CustomerModel();

        ObservableList<SupplierTable> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDto> dtoList = supplierDAO.getAllSupplier();

            for (SupplierDto dto : dtoList) {
                obList.add(
                        new SupplierTable(
                                dto.getSupplierId(),
                                dto.getPlantName(),
                                dto.getPlantId(),
                                dto.getPlantCount(),
                                dto.getPrice()

                        )
                );
            }

            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        clmSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        clmPlantName.setCellValueFactory(new PropertyValueFactory<>("PlantName"));
        clmPlantId.setCellValueFactory(new PropertyValueFactory<>("plantId"));
        clmPlantCount.setCellValueFactory(new PropertyValueFactory<>("PlantCount"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }

    private void clearFields() {
        txtsuplierId.setText("");
        txtplantName.setText("");
        txtplantId.setText("");
        txtplantCount.setText("");
        txtprice.setText("");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dash Board");
        stage.centerOnScreen();
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtsuplierId.getText();

//        var model = new CustomerModel();
        try {
            boolean isDeleted = supplierDAO.deleteSupplier(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted!").show();
                loadAllSupplier();
                setCellValueFactory();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void txtSupplierIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INVOICE,txtsuplierId);
    }

    public void txtPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,txtprice);
    }

    public void txtPlantNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtplantName);
    }

    public void txtPlantCountOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INTEGER,txtplantCount);
    }

    public void txtPlantIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INVOICE,txtplantId);
    }
}

