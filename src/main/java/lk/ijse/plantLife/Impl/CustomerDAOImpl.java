package lk.ijse.plantLife.Impl;

import lk.ijse.plantLife.dao.CustomerDAO;
import lk.ijse.plantLife.dto.CustomerDto;
import lk.ijse.plantLife.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
//    public  boolean SaveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
//        return SQLUtil.execute("INSERT INTO customer (CustomerID,CustomerName,Address,PhoneNumber) VALUES (?,?,?,?)", customerDto.getId(), customerDto.getName(), customerDto.getAddress(), customerDto.getAddress());
//    }
//    public boolean delete(String CustomerID) throws SQLException, ClassNotFoundException {
//        return SQLUtil.execute("DELETE FROM customer WHERE id=?" ,CustomerID);
//    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        ArrayList<CustomerDto>getAllCustomer = new ArrayList<>();
        while (rst.next()){
            CustomerDto customerDto = new CustomerDto(rst.getString("CustomerID"), rst.getString("CustomerName"), rst.getString("Address"), rst.getString("PhoneNumber"));
            getAllCustomer.add(customerDto);
        }
        return null; //meka nidimatha nisa null.
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer (CustomerID,CustomerName,Address,PhoneNumber) VALUES (?,?,?,?)", dto.getId(), dto.getName(), dto.getAddress(), dto.getAddress());
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET CustomerID = ?, CustomerName = ?, Address = ?, PhoneNumber = ? WHERE CustomerID = ?" , dto.getId(), dto.getName(), dto.getAddress(), dto.getPhoneNum());

    }




    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE CustomerID=?" ,id);
    }
}
