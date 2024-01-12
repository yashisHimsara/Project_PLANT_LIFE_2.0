package lk.ijse.plantLife.dao;

import lk.ijse.plantLife.Impl.SQLUtil;
import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.OrderDTO;
import lk.ijse.plantLife.dto.OrdersDto;
import lk.ijse.plantLife.model.PlantModel;
import lk.ijse.plantLife.model.PlantOrderModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderDAO extends SuperDAO {
      boolean saveOrders(OrderDTO ordersDto) throws SQLException, ClassNotFoundException;
      boolean placeOrder(OrderDTO ob) throws SQLException;
     OrdersDto searchOrder(String OrderID) throws SQLException, ClassNotFoundException;
     List<OrdersDto> getAllOrders() throws SQLException;
     boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;
}
