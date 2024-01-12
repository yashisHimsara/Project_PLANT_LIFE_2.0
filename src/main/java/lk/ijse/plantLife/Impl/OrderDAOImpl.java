package lk.ijse.plantLife.Impl;

import lk.ijse.plantLife.dao.OrderDAO;
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

public class OrderDAOImpl implements OrderDAO {
    public boolean saveOrders(OrderDTO ordersDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES (?,?,?,?)", ordersDto.getOrderId(), ordersDto.getOrderDate(), ordersDto.getCustomerId(), ordersDto.getCustomerId());
    }

    public boolean placeOrder(OrderDTO ob) throws SQLException {
        DbConnection.getInstance().getConnection().setAutoCommit(false);

        try {
            boolean isOrderSaved = saveOrders(ob);
            if (isOrderSaved){
                boolean isALlSaved = PlantOrderModel.save(ob.getList());
                if (isALlSaved){
                    boolean isQtyUpdated = PlantModel.updateQty(ob.getList());
                    if (isQtyUpdated){
                        DbConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DbConnection.getInstance().getConnection().rollback();
        }catch (Exception e){
            DbConnection.getInstance().getConnection().rollback();
            e.printStackTrace();
        }finally {
            DbConnection.getInstance().getConnection().setAutoCommit(true);
        }
        return false;
    }


    public OrdersDto searchOrder(String OrderID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT * FROM orders WHERE OrderID = ?", OrderID);
    }

    public List<OrdersDto> getAllOrders() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM orders";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<OrdersDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new OrdersDto(
                            resultSet.getString(1),
                            resultSet.getDate(2).toLocalDate(),
                            resultSet.getString(3),
                            resultSet.getString(4)

                    )
            );
        }
        return dtoList;
    }
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM orders WHERE OrderID = ?", id);
    }
}
