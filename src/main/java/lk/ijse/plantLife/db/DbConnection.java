package lk.ijse.plantLife.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static DbConnection dbConnection;
    private  Connection connection;

    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/plant_life",
                "root",
                "1234"
        );
    }
    public static DbConnection getInstance() throws SQLException {
        return (null == dbConnection) ? dbConnection = new DbConnection() : dbConnection;
    }
//    public static DbConnection getDbConnection() throws SQLException, ClassNotFoundException {
//    return dbConnection == null ? dbConnection= new DbConnection() : dbConnection;
//}
    public Connection getConnection() {
        return connection;
    }
}

