package lk.ijse.plantLife.model;

import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.PlantDto;
import lk.ijse.plantLife.dto.plantOrderDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlantModel {

//    public boolean SavePlant(PlantDto plantDto) throws SQLException {
//        Connection connection= DbConnection.getInstance().getConnection();
//
//        String sql = "INSERT INTO plant VALUES (?,?,?,?,?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1,plantDto.getPlantId());
//        preparedStatement.setString(2,plantDto.getPlantName());
//        preparedStatement.setDouble(3,plantDto.getPrice());
//        preparedStatement.setString(4, plantDto.getPlantAge());
//        preparedStatement.setString(5,plantDto.getPlantCount());
//        boolean isSaved = preparedStatement.executeUpdate()>0;
//        return isSaved;
//           }
//
//    public static List<PlantDto> getAllPlant() throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "SELECT * FROM plant";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        ResultSet resultSet = pstm.executeQuery();
//
//        ArrayList<PlantDto> dtoList = new ArrayList<>();
//
//        while(resultSet.next()) {
//            dtoList.add(
//                    new PlantDto(
//                            resultSet.getString(1),
//                            resultSet.getString(2),
//                            resultSet.getDouble(3),
//                            resultSet.getString(4),
//                            resultSet.getString(5)
//                    )
//            );
//        }
//        return dtoList;
//    }
//    public boolean deletePlant(String id) throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "DELETE FROM plant WHERE PlantID = ?";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setString(1,id);
//
//        return pstm.executeUpdate() > 0;
//    }
//    public boolean updatePlant(PlantDto dto) throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "UPDATE plant SET PlantID = ?, PlantName = ?, Price = ?, PlantAge = ?, PlantCount = ? WHERE PlantID = ?";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setString(1, dto.getPlantId());
//        pstm.setString(2, dto.getPlantName());
//        pstm.setDouble(3, dto.getPrice());
//        pstm.setString(4, dto.getPlantAge());
//        pstm.setString(5,dto.getPlantCount());
//
//        return pstm.executeUpdate() > 0;
//    }
       public static boolean updateCount(String plantId, int plantCount) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE plant SET PlantCount = PlantCount - ? WHERE PlantID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, plantCount);
        pstm.setString(2, plantId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateQty(List<plantOrderDto> list) throws SQLException {
        for (plantOrderDto ob : list) {
            if (!updateQty(ob)){
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(plantOrderDto ob) throws SQLException {
        PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(
                "update plant set PlantCount = PlantCount - ? where PlantID = ?");

        ps.setInt(1,ob.getQty());
        ps.setString(2,ob.getPlantId());

        return ps.executeUpdate()>0;

    }


}
