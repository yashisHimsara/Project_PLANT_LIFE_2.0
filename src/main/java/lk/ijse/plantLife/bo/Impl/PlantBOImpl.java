package lk.ijse.plantLife.bo.Impl;

import lk.ijse.plantLife.dao.DAOFactory;
import lk.ijse.plantLife.dao.PlantDAO;
import lk.ijse.plantLife.dto.PlantDto;
import lk.ijse.plantLife.entity.Plant;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlantBOImpl {
    PlantDAO plantDAO =
            (PlantDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.PLANT);
    public ArrayList<PlantDto> getAllPlant() throws SQLException, ClassNotFoundException {
        ArrayList<Plant> plants =plantDAO.getAll();
        ArrayList<PlantDto> plantDtos=new ArrayList<>();
        for (Plant plant:plants) {
            plantDtos.add(new PlantDto(plant.getPlantAge(), plant.getPlantName(), plant.getPrice(), plant.getPlantAge(), plant.getPlantCount()));
        }
        return plantDtos;
    }
    public boolean savePlant(PlantDto dto) throws SQLException, ClassNotFoundException {
        return plantDAO.save(new Plant(dto.getPlantId(), dto.getPlantName(), dto.getPrice(), dto.getPlantAge(), dto.getPlantCount()));
    }
    public boolean updatePlant(PlantDto dto) throws SQLException, ClassNotFoundException {
        return plantDAO.update(new Plant(dto.getPlantId(), dto.getPlantName(),dto.getPrice(), dto.getPlantAge(), dto.getPlantCount()));
    }
    public boolean deletePlant(String id) throws SQLException, ClassNotFoundException {
        return plantDAO.delete(id);
    }
}
