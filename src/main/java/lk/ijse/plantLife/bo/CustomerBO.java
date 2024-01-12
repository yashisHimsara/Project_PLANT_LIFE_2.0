package lk.ijse.plantLife.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.plantLife.dto.CustomerDto;
public interface CustomerBO {
    ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDto customerDTO) throws SQLException, ClassNotFoundException ;
    Object updateCustomer(CustomerDto customerDTO) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
}
