package lk.ijse.plantLife.model;

import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.CustomerDto;
import lk.ijse.plantLife.dto.OrderDTO;
import lk.ijse.plantLife.dto.OrdersDto;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersModel {

//    public static boolean saveOrders(OrderDTO ordersDto) throws SQLException , ClassCastException {
//        Connection connection= DbConnection.getInstance().getConnection();
//        String sql="INSERT INTO orders VALUES (?,?,?,?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1,ordersDto.getOrderId());
//        preparedStatement.setDate(2, ordersDto.getOrderDate());
//        preparedStatement.setInt(3,ordersDto.getPlantCount());
//        preparedStatement.setString(4,ordersDto.getCustomerId());
//        boolean isSaved=preparedStatement.executeUpdate()>0;
//        return isSaved;
//    }
//
//    public static boolean placeOrder(OrderDTO ob) throws SQLException {
//        DbConnection.getInstance().getConnection().setAutoCommit(false);
//
//        try {
//            boolean isOrderSaved = saveOrders(ob);
//            if (isOrderSaved){
//                boolean isALlSaved = PlantOrderModel.save(ob.getList());
//                if (isALlSaved){
//                    boolean isQtyUpdated = PlantModel.updateQty(ob.getList());
//                    if (isQtyUpdated){
//                        DbConnection.getInstance().getConnection().commit();
//                        return true;
//                    }
//                }
//            }
//            DbConnection.getInstance().getConnection().rollback();
//        }catch (Exception e){
//            DbConnection.getInstance().getConnection().rollback();
//            e.printStackTrace();
//        }finally {
//            DbConnection.getInstance().getConnection().setAutoCommit(true);
//        }
//        return false;
//    }
//
//
//    public OrdersDto searchOrder(String OrderID) throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        String sql = "SELECT * FROM orders WHERE OrderID = ?";
//
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setString(1, OrderID);
//
//        ResultSet resultSet = pstm.executeQuery();
//
//        OrdersDto dto = null;
//
//        if(resultSet.next()) {
//            dto = new OrdersDto(
//                    resultSet.getString(1),
//                    resultSet.getDate(2).toLocalDate(),
//                    resultSet.getString(3),
//                    resultSet.getString(4)
//
//            );
//        }
//        return dto;
//    }
//
//    public List<OrdersDto> getAllOrders() throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "SELECT * FROM orders";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        ResultSet resultSet = pstm.executeQuery();
//
//        ArrayList<OrdersDto> dtoList = new ArrayList<>();
//
//        while(resultSet.next()) {
//            dtoList.add(
//                    new OrdersDto(
//                            resultSet.getString(1),
//                            resultSet.getDate(2).toLocalDate(),
//                            resultSet.getString(3),
//                            resultSet.getString(4)
//
//                    )
//            );
//        }
//        return dtoList;
//    }
//    public boolean deleteOrder(String id) throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "DELETE FROM orders WHERE OrderID = ?";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setString(1,id);
//
//        return pstm.executeUpdate() > 0;
//    }
}
