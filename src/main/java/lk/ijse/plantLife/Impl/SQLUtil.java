package lk.ijse.plantLife.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import com.example.layeredarchitecture.db.DBConnection;
import lk.ijse.plantLife.db.DbConnection;
public class SQLUtil {
    public static <T>T execute(String sql, Object... ob) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i=0;i < ob.length;i++){
            pstm.setObject((i+1),ob[i]);
        }

        if (sql.startsWith("SELECT")){
            return (T) pstm.executeQuery();
        }else {
            return (T)(Boolean)(pstm.executeUpdate()>0);
        }
    }
}