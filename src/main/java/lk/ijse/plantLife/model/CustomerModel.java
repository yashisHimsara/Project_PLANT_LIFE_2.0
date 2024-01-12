package lk.ijse.plantLife.model;

import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.CustomerDto;
import lk.ijse.plantLife.dto.PlantDto;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

//    public boolean SaveCustomer(CustomerDto customerDto) throws Exception {
//        Connection connection= DbConnection.getInstance().getConnection();
//        String sql="INSERT INTO customer VALUES (?,?,?,?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1,customerDto.getId());
//        preparedStatement.setString(2,customerDto.getName());
//        preparedStatement.setString(3,customerDto.getAddress());
//        preparedStatement.setString(4,customerDto.getPhoneNum());
//        boolean isSaved=preparedStatement.executeUpdate()>0;
//        return isSaved;
//    }

    public CustomerDto searchCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection ();

        String sql = "SELECT * FROM customer WHERE CustomerID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        CustomerDto dto = null;

        if(resultSet.next()) {
            String CustomerID = resultSet.getString(1);
            String CustomerName = resultSet.getString(2);
            String Address = resultSet.getString(3);
            String PhoneNumber = resultSet.getString(4);

            dto = new CustomerDto(CustomerID, CustomerName, Address, PhoneNumber);
        }
        return dto;
    }
//    public List<CustomerDto> getAllCustomer() throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "SELECT * FROM customer";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        ResultSet resultSet = pstm.executeQuery();
//
//        ArrayList<CustomerDto> dtoList = new ArrayList<>();
//
//        while(resultSet.next()) {
//            dtoList.add(
//                    new CustomerDto(
//                            resultSet.getString(1),
//                            resultSet.getString(2),
//                            resultSet.getString(3),
//                            resultSet.getString(4)
//                    )
//            );
//        }
//        return dtoList;
//    }
//    public boolean deleteCustomer(String id) throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "DELETE FROM customer WHERE CustomerID = ?";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setString(1,id);
//
//        return pstm.executeUpdate() > 0;
//    }
//    public boolean updateCustomer(CustomerDto customerDto) throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "UPDATE customer SET CustomerID = ?, CustomerName = ?, Address = ?, PhoneNumber = ? WHERE CustomerID = ?";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setString(1, customerDto.getId());
//        pstm.setString(2, customerDto.getName());
//        pstm.setString(4, customerDto.getAddress());
//        pstm.setString(5,customerDto.getPhoneNum());
//
//        return pstm.executeUpdate() > 0;
//    }

}
