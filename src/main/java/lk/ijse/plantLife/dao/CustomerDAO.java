package lk.ijse.plantLife.dao;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.plantLife.Impl.SQLUtil;
import lk.ijse.plantLife.dto.CustomerDto;
import lk.ijse.plantLife.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer>{
    /* ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDto customerDTO) throws SQLException, ClassNotFoundException;
     Object updateCustomer(CustomerDto customerDTO) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;*/
}
