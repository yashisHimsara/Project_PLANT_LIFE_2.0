package lk.ijse.plantLife.model;

import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.PayDto;
import lk.ijse.plantLife.dto.PlantDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayModel {

    public boolean SavePayment(PayDto payDto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO payment VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,payDto.getPaymentId());
        preparedStatement.setString(2, String.valueOf(payDto.getDate()));
        preparedStatement.setDouble(3,payDto.getPrice());
        preparedStatement.setString(4,payDto.getSoldPlant());
        preparedStatement.setString(5,payDto.getPlantCount());
        preparedStatement.setString(6,payDto.getOrderId());
        preparedStatement.setString(7,payDto.getCustomerId());

        boolean isSaved = preparedStatement.executeUpdate()>0;
        return isSaved;
    }

    public List<PayDto> getAllPayment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM payment";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<PayDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new PayDto(
                            resultSet.getString(1),
                            resultSet.getDate(2).toLocalDate(),
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)
                    )
            );
        }
        return dtoList;
    }
    public String getPlantName (String id)throws SQLException { String name;
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT Orders.OrderID, Plant.PlantName name\n" +
                "FROM Orders\n" +
                "         JOIN PlantOrder ON Orders.OrderID = PlantOrder.OrderID\n" +
                "         JOIN Plant ON PlantOrder.PlantID = Plant.PlantID where Orders.OrderID = ?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
             name = resultSet.getString("name");
             return name;
        }
        return null;
    }

//   public boolean UpdatePayment(PayDto dto) throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        String sql = "UPDATE payment SET PaymentID = ?, SoldPlants = ?, Date = ?, Price = ? WHERE PaymentID = ?";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setString(1, dto.getPaymentId());
//        pstm.setString(2, dto.getSoldPlant());
//        pstm.setString(3,dto.getPalntCount());
//        pstm.setString(4,dto.getDate());
//        pstm.setInt(5, (int) dto.getPrice());
//
//        return pstm.executeUpdate() > 0;
//    }

    public boolean deletePayment(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM payment WHERE PaymentID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        return pstm.executeUpdate() > 0;
    }
}
