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
import javafx.util.StringConverter;
import lk.ijse.plantLife.Impl.CustomerDAOImpl;
import lk.ijse.plantLife.Impl.OrderDAOImpl;
import lk.ijse.plantLife.Impl.PlantDAOImpl;
import lk.ijse.plantLife.bo.Impl.PlantBOImpl;
import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.*;
import lk.ijse.plantLife.dto.tm.CartTM;
import lk.ijse.plantLife.model.CustomerModel;
import lk.ijse.plantLife.model.OrdersModel;
import lk.ijse.plantLife.model.PlantModel;
import lk.ijse.plantLife.utill.Regex;
import lk.ijse.plantLife.utill.TextFields;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersFromController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<CustomerDto> cmbCustomerId;

    @FXML
    private ComboBox<PlantDto> cmbPlants;

    @FXML
    private TableColumn<CartTM,String> colPlantId;

    @FXML
    private TableColumn<CartTM,String> colPlantName;

    @FXML
    private TableColumn<CartTM,Double> colPrice;

    @FXML
    private TableColumn<CartTM,Integer> colQty;

    @FXML
    private TableColumn<CartTM,Double> colSubTotal;

    @FXML
    private TableColumn<CartTM,Button> colSubTotal1;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CartTM> tblPlants;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtPlantCount;

    @FXML
    private TextField txtAvailableQty;

    PlantDAOImpl plantDAO = new PlantDAOImpl();
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    OrderDAOImpl orderDAO = new OrderDAOImpl();
    PlantBOImpl plantBO = new PlantBOImpl();

    public void initialize(){
        setDataForComboBoxes();
        setConvertersForComboBoxes();
        setCellValues();
    }

    public void setCellValues(){
        colPlantId.setCellValueFactory(new PropertyValueFactory<>("plantID"));
        colPlantName.setCellValueFactory(new PropertyValueFactory<>("plantName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        colSubTotal1.setCellValueFactory(new PropertyValueFactory<>("action"));

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/DashBoard.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dash Board");
        stage.centerOnScreen();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtOrderId.getText();

//        var model = new CustomerModel();
        try {
            boolean isDeleted =orderDAO.deleteOrder(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "order not deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        var obj = new Object(){
            int total= 0;
        };
//        List<plantOrderDto> collect = tblPlants.getItems().stream().map(e -> {
//            plantOrderDto ob = new plantOrderDto();
//            ob.setOrderId(txtOrderId.getText());
//            ob.setPlantId(e.getPlantID());
//            ob.setQty(e.getQty());
//            obj.total+=ob.getQty();
//            ob.setPlantDto(e.getPlant());
//            return ob;
//        }).collect(Collectors.toList());

        ObservableList<CartTM> items = tblPlants.getItems();
        List<plantOrderDto> collect = new ArrayList<>();
        for (CartTM e: items ) {
            plantOrderDto ob = new plantOrderDto();
            ob.setOrderId(txtOrderId.getText());
            ob.setPlantId(e.getPlantID());
            ob.setQty(e.getQty());
            obj.total+=ob.getQty();
            ob.setPlantDto(e.getPlant());
            collect.add(ob);
        }

//        for (int i = 0; i < items.size(); i++) {
//            CartTM e = items.get(i);
//            plantOrderDto ob = new plantOrderDto();
//            ob.setOrderId(txtOrderId.getText());
//            ob.setPlantId(e.getPlantID());
//            ob.setQty(e.getQty());
//            obj.total+=ob.getQty();
//            ob.setPlantDto(e.getPlant());
//            collect.add(ob);
//
//        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(txtOrderId.getText());
        orderDTO.setOrderDate(Date.valueOf(LocalDate.now()));
        orderDTO.setCustomerId(cmbCustomerId.getSelectionModel().getSelectedItem().getId());
        orderDTO.setPlantCount(obj.total);
        orderDTO.setList(collect);

        try {
            boolean isPlaced = orderDAO.placeOrder(orderDTO);
            if (isPlaced){
                new Alert(Alert.AlertType.INFORMATION,"Order Placed Success").show();
                tblPlants.getItems().clear();
                setDataForComboBoxes();
                clear();
            }else {
                new Alert(Alert.AlertType.ERROR,"Order Placing Failed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        PlantDto selectedItem = cmbPlants.getSelectionModel().getSelectedItem();
        if (selectedItem==null){
            new Alert(Alert.AlertType.ERROR,"Select Item To Add").show();
            return;
        }

        boolean b = Regex.setTextColor(TextFields.INTEGER, txtPlantCount);
        boolean flag = false;
        if (b){
            int value = Integer.parseInt(txtAvailableQty.getText()) - Integer.parseInt(txtPlantCount.getText());
            System.out.println(value);
            if (value>=0){
                flag=true;
            }
        }

        if (!flag){
            new Alert(Alert.AlertType.ERROR,"Select Item To Add").show();
            return;
        }

        int qty = Integer.parseInt(txtPlantCount.getText());
        selectedItem.setPlantCount(String.valueOf(Integer.parseInt(selectedItem.getPlantCount())-qty));
        for (CartTM item : tblPlants.getItems()) {
            if (item.getPlantID().equals(selectedItem.getPlantId())){
                item.setQty(item.getQty()+qty);
                tblPlants.refresh();
                clear();
                return;
            }
        }
        CartTM cartTM = new CartTM();
        cartTM.setPlantID(selectedItem.getPlantId());
        cartTM.setPlantName(selectedItem.getPlantName());
        cartTM.setPrice(selectedItem.getPrice());
        cartTM.setQty(qty);
        cartTM.setSubTotal(cartTM.getPrice()*cartTM.getQty());
        cartTM.setPlant(selectedItem);
        Button delete = new Button("Delete");
        delete.setOnAction(e->{
            tblPlants.getItems().remove(cartTM);
            selectedItem.setPlantCount(String.valueOf(Integer.parseInt(selectedItem.getPlantCount())+cartTM.getQty()));
        });
        cartTM.setAction(delete);
        tblPlants.getItems().add(cartTM);
        clear();
    }

    public void clear(){
        txtAvailableQty.clear();
        txtPlantCount.clear();
        cmbPlants.getSelectionModel().clearSelection();
    }

    @FXML
    void btnUpdateonAction(ActionEvent event) {

    }

    @FXML
    void txtOrderIdOnKryReleased(KeyEvent event) {

    }

    @FXML
    void txtPlantCountOnKryReleased(KeyEvent event) {
        boolean b = Regex.setTextColor(TextFields.INTEGER, txtPlantCount);
        boolean flag = false;
        if (b){
            int value = Integer.parseInt(txtAvailableQty.getText()) - Integer.parseInt(txtPlantCount.getText());
            System.out.println(value);
            if (value>=0){
               flag=true;
            }
        }
        btnSave.setDisable(!flag);

    }

    @FXML
    void txtSoldPlantOnKryReleased(KeyEvent event) {

    }

    public void setDataForComboBoxes(){
        try {
            cmbPlants.setItems(FXCollections.observableArrayList(plantBO.getAllPlant()));
            cmbCustomerId.setItems(FXCollections.observableArrayList(new CustomerDAOImpl.getAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setConvertersForComboBoxes(){
        cmbCustomerId.setConverter(new StringConverter<CustomerDto>() {
            @Override
            public String toString(CustomerDto customerDto) {
                return customerDto==null ? "" : customerDto.getId().concat(":").concat(customerDto.getName());
            }

            @Override
            public CustomerDto fromString(String s) {
                return null;
            }

        });

        cmbPlants.setConverter(new StringConverter<PlantDto>() {
            @Override
            public String toString(PlantDto plantDto) {
                return plantDto==null ? "" : plantDto.getPlantId()+" : "+plantDto.getPlantName();
            }

            @Override
            public PlantDto fromString(String s) {
                return null;
            }
        });
    }


    public void btnReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/Orders.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasprePrint = JasperFillManager.fillReport(jasperReport,
                null,
                DbConnection.getInstance().getConnection()
        );
        JasperViewer.viewReport(jasprePrint,false);
    }
    public void cmbPlantOnAction(ActionEvent actionEvent) {
        PlantDto selectedItem = cmbPlants.getSelectionModel().getSelectedItem();
        if (selectedItem!=null){
            txtAvailableQty.setText(selectedItem.getPlantCount());
            //txtPlantCount.setText(selectedItem.getPlantCount());

        }
    }


}
