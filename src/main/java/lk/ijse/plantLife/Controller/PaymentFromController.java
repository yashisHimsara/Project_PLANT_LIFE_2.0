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
import lk.ijse.plantLife.Impl.CustomerDAOImpl;
import lk.ijse.plantLife.Impl.OrderDAOImpl;
import lk.ijse.plantLife.bo.Impl.CustomerBOImpl;
import lk.ijse.plantLife.dto.CustomerDto;
import lk.ijse.plantLife.dto.OrdersDto;
import lk.ijse.plantLife.dto.PayDto;

import lk.ijse.plantLife.dto.tm.CustomerTable;
import lk.ijse.plantLife.dto.tm.PayTable;
import lk.ijse.plantLife.model.CustomerModel;
import lk.ijse.plantLife.model.OrdersModel;
import lk.ijse.plantLife.model.PayModel;
import lk.ijse.plantLife.model.PaymentModel;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PaymentFromController {

    @FXML
    private TableView<PayTable> tblPay;

    @FXML
    private AnchorPane root;


    @FXML
    private DatePicker txtDate;

    @FXML
    private Label lblSoldPlantset;

    @FXML
    private Label lblPlantCountset;

    @FXML
    private Label lblPaymentIdset;

    @FXML
    private Label lblCustomerNameset;

    @FXML
    private TableColumn<?, ?> clmdate;

    @FXML
    private TableColumn<?, ?> clmpaymentId;

    @FXML
    private TableColumn<?, ?> clmplantCount;

    @FXML
    private TableColumn<?, ?> clmprice;

    @FXML
    private TableColumn<?, ?> clmsoldPlants;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbOrderId;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblPlantCount;

    @FXML
    private Label lblSoldPlant;

    @FXML
    private Label lblprice;

    @FXML
    private TextField txtPrice;

    private CustomerModel customerModel = new CustomerModel();
    private OrdersModel ordersModel = new OrdersModel();
    private PaymentModel paymentModel = new PaymentModel();
    private static PayDto payDto = new PayDto();
    private static PayModel payModel = new PayModel();
    OrderDAOImpl orderDAO = new OrderDAOImpl();
    CustomerBOImpl customerBO = new CustomerBOImpl();
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    private ObservableList<PayTable> obList = FXCollections.observableArrayList();

    public void initialize() {
        setCellValueFactory();
        generateNextPaymentID();
        loadOrders();
        loadCustomer();
    }

    private void generateNextPaymentID() {
        try {
            String paymentId = paymentModel.generateNextPaymentId();
            lblPaymentIdset.setText(paymentId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadOrders() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<OrdersDto> ordersDtos = orderDAO.getAllOrders();

            for (OrdersDto dto : ordersDtos) {
                obList.add(dto.getOrderId());
            }
            cmbOrderId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomer() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> idList = customerBO.getAllCustomer();

            for (CustomerDto dto : idList) {
                obList.add(dto.getId());
            }

            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String id = cmbCustomerId.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            CustomerDto customerDto = customerModel.searchCustomer(id);
            lblCustomerNameset.setText(customerDto.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void cmbOrderIdOnAction(ActionEvent actionEvent) {

        String orderId = cmbOrderId.getValue();
//        CustomerModel customerModel = new CustomerModel();
        try {
            OrdersDto ordersDto = orderDAO.searchOrder(orderId);
           //
            String plantName = payModel.getPlantName(orderId);
            lblSoldPlantset.setText(plantName);
            lblPlantCountset.setText(ordersDto.getPlantCount());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dash Board");
        stage.centerOnScreen();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String paymentId = lblPaymentIdset.getText();
        LocalDate date = txtDate.getValue();
        int price = Integer.parseInt(txtPrice.getText());
        String soldPlant = lblSoldPlantset.getText();
        String plantCount = lblPlantCountset.getText();
        String orderId = cmbOrderId.getValue();
        String customerId = cmbCustomerId.getValue();


        payDto = new PayDto(paymentId, date, price, soldPlant, plantCount, orderId, customerId);

        try {
            boolean isSaved = payModel.SavePayment(payDto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment is Saved").show();
                // clearFields();
                loadAllPayment();
                setCellValueFactory();
            }
        } catch (Exception e) {
            //throw new RuntimeException(e);
            new Alert(Alert.AlertType.CONFIRMATION, e.getMessage()).show();
        }
    }

    private void loadAllPayment() {
        //var model = new CustomerModel();

        ObservableList<PayTable> obList = FXCollections.observableArrayList();

        try {
            List<PayDto> dtoList = payModel.getAllPayment();

            for (PayDto dto : dtoList) {
                obList.add(
                        new PayTable(
                                dto.getPaymentId(),
                                dto.getDate(),
                                dto.getPrice(),
                                dto.getSoldPlant(),
                                dto.getPlantCount(),
                                dto.getOrderId(),
                                dto.getCustomerId()
                        )
                );
            }

            tblPay.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        clmpaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        clmsoldPlants.setCellValueFactory(new PropertyValueFactory<>("soldPlant"));
        clmplantCount.setCellValueFactory(new PropertyValueFactory<>("plantCount"));
        clmdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        clmprice.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }
}