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
import lk.ijse.plantLife.Impl.PlantDAOImpl;
import lk.ijse.plantLife.bo.Impl.PlantBOImpl;
import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.PlantDto;
import lk.ijse.plantLife.dto.SupplierDto;
import lk.ijse.plantLife.dto.tm.PlantTable;
import lk.ijse.plantLife.dto.tm.SupplierTable;
import lk.ijse.plantLife.model.PlantModel;
import lk.ijse.plantLife.utill.Regex;
import lk.ijse.plantLife.utill.TextFields;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class PlantsFromController {
    public AnchorPane root;

    @FXML
    private Label WelcomeLable;

    @FXML
    private Button btnSave;

    @FXML
    private Label shopLable;

    @FXML
    private TableColumn<?, ?> clmPlantAge;

    @FXML
    private TableColumn<?, ?> clmPlantCount;

    @FXML
    private TableColumn<?, ?> clmPlantId;

    @FXML
    private TableColumn<?, ?> clmPlantName;

    @FXML
    private TableColumn<?, ?> clmPrice;

    @FXML
    private TableView<PlantTable> tblPlant;

    @FXML
    private TextField txtplantAge;

    @FXML
    private TextField txtplantId;

    @FXML
    private TextField txtplantName;

    @FXML
    private TextField txtplantCount;

    @FXML
    private TextField txtprice;

    private static PlantDto plantDto = new PlantDto();
    private static PlantModel plantModel = new PlantModel();
    //PlantDAOImpl plantDAO = new PlantDAOImpl();
    PlantBOImpl plantBO = new PlantBOImpl();

    public void initialize() {
        setCellValueFactory();
        loadAllPlant();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String plantId = txtplantId.getText();
        String plantName = txtplantName.getText();
        double price = Integer.parseInt(txtprice.getText());
        String plantAge = txtplantAge.getText();
        String plantCount = txtplantCount.getText();

        plantDto = new PlantDto(plantId, plantName, price , plantAge, plantCount);

        try {
            boolean isSaved = plantBO.savePlant(plantDto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Plant is Saved").show();
                loadAllPlant();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            new Alert(Alert.AlertType.CONFIRMATION, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllPlant() {
        //var model = new CustomerModel();

        ObservableList<PlantTable> obList = FXCollections.observableArrayList();

        try {
            List<PlantDto> dtoList = plantBO.getAllPlant();

            for (PlantDto dto : dtoList) {
                obList.add(
                        new PlantTable(
                                dto.getPlantId(),
                                dto.getPlantName(),
                                dto.getPrice(),
                                dto.getPlantAge(),
                                dto.getPlantCount()


                                )
                );
            }

            tblPlant.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        clmPlantId.setCellValueFactory(new PropertyValueFactory<>("plantId"));
        clmPlantName.setCellValueFactory(new PropertyValueFactory<>("plantName"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmPlantAge.setCellValueFactory(new PropertyValueFactory<>("plantAge"));
        clmPlantCount.setCellValueFactory(new PropertyValueFactory<>("plantCount"));
    }

    private void clearFields() {
        txtplantId.setText("");
        txtplantName.setText("");
        txtprice.setText("");
        txtplantAge.setText("");
        txtplantCount.setText("");
    }

    public void btnOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dash Board");
        stage.centerOnScreen();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtplantId.getText();

//        var model = new CustomerModel();
        try {
            boolean isDeleted = plantBO.deletePlant(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Plant deleted!").show();
                loadAllPlant();
                setCellValueFactory();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Plant not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws NumberFormatException {
        String plantId = txtplantId.getText();
        String plantName = txtplantName.getText();
        int price = Integer.parseInt(txtprice.getText());
        String plantAge = txtplantAge.getText();
        String plantCount = txtplantCount.getText();

        var dto = new PlantDto(plantId, plantName, price , plantAge , plantCount);

//        var model = new CustomerModel();
        try {
            boolean isUpdated = plantBO.updatePlant(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Plants updated!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void txtPlantIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INVOICE, txtplantId);

    }

    public void txtPlantNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME, txtplantName);
    }

    public void txtPlantCountOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INTEGER, txtplantCount);
    }

    public void txtPlantAgeOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INVOICE, txtplantAge);
    }

}

