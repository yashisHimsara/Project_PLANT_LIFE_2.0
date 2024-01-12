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
import lk.ijse.plantLife.Impl.CustomerDAOImpl;
import lk.ijse.plantLife.bo.Impl.CustomerBOImpl;
import lk.ijse.plantLife.dao.CustomerDAO;
import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.CustomerDto;
import lk.ijse.plantLife.dto.PlantDto;
import lk.ijse.plantLife.dto.tm.CustomerTable;
import lk.ijse.plantLife.model.CustomerModel;
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

public class CustomerFromController {
    public AnchorPane root;

    @FXML
    private TableColumn<?, ?> clmAddress;

    @FXML
    private TableColumn<?, ?> clmCustomerId;

    @FXML
    private TableColumn<?, ?> clmCustomerName;

    @FXML
    private TableColumn<?, ?> clmPhoneNumber;

    @FXML
    private TableView<CustomerTable> tblCustomer;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtPhoneNum;

    private static CustomerDto customerDto=new CustomerDto();
    private static CustomerModel customerModel=new CustomerModel();
    CustomerBOImpl customerBO = new CustomerBOImpl();
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    public void initialize() {
        setCellValueFactory();
        loadAllCustomer();
    }

    public  void btnSaveOnAction() {
        String id=txtCustomerID.getText();
        String name=txtCustomerName.getText();
        String address = txtAddress.getText();
        String phoneNumber= txtPhoneNum.getText();

        customerDto=new CustomerDto(id,name,address,phoneNumber);


        try {
            boolean isSaved = customerBO.saveCustomer(customerDto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer is Saved").show();
                clearFields();
                loadAllCustomer();
                setCellValueFactory();
            }
        } catch (Exception e) {
            //throw new RuntimeException(e);
            new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
        }

    }
    public void loadAllCustomer() {
        //var model = new CustomerModel();

        ObservableList<CustomerTable> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> dtoList = customerBO.getAllCustomer();

            for (CustomerDto dto : dtoList) {
                obList.add(
                        new CustomerTable(
                                dto.getId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getPhoneNum()
                        )
                );
            }

            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        clmCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    private void clearFields() {
        txtCustomerID.setText("");
        txtCustomerName.setText("");
        txtAddress.setText("");
        txtPhoneNum.setText("");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dash Board");
        stage.centerOnScreen();
    }
    public void btnDeleteOnAction(ActionEvent event) {
        String id = txtCustomerID.getText();

//        var model = new CustomerModel();
        try {
            boolean isDeleted = customerBO.deleteCustomer(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                loadAllCustomer();
                setCellValueFactory();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "customer not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

   public  void btnUpdateOnAction() {
        String customerId = txtCustomerID.getText();
        String customerName = txtCustomerName.getText();
        String address = txtAddress.getText();
        String phoneNum = txtPhoneNum.getText();

        var dto = new CustomerDto(customerId , customerName , address, phoneNum);

//        var model = new CustomerModel();
        try {
            boolean isUpdated = (boolean) customerBO.updateCustomer(customerDto);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void txtSearchOnAction() {
        String id = txtCustomerID.getText();

//        var model = new CustomerModel();
        try {
            CustomerDto customerDto = customerModel.searchCustomer(id);
//            System.out.println(customerDto);
            if (customerDto != null) {
                txtCustomerID.setText(customerDto.getId());
                txtCustomerName.setText(customerDto.getName());
                txtAddress.setText(customerDto.getAddress());
                txtPhoneNum.setText(customerDto.getPhoneNum());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void btnReportOnAction() throws JRException, SQLException {
       InputStream resourceAsStream = getClass().getResourceAsStream("/report/Customer.jrxml");
       JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport =JasperCompileManager.compileReport(load);
       JasperPrint jasprePrint = JasperFillManager.fillReport(jasperReport,
                          null,
                DbConnection.getInstance().getConnection()
        );
        JasperViewer.viewReport(jasprePrint,false);
    }

    public void txtCustomerIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INVOICE,txtCustomerID);
    }

    public void txtAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    public void txtPhoneNumberOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PHONE,txtPhoneNum);
    }

    public void txtCustomerNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtCustomerName);
    }

}
