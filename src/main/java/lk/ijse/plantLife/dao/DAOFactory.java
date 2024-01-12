package lk.ijse.plantLife.dao;

import lk.ijse.plantLife.Impl.CustomerDAOImpl;
import lk.ijse.plantLife.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory =new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,PLANT,ORDER,INVENTORY,SUPPLIER
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case PLANT:
                return new PlantDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case INVENTORY:
                return new InventoryDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            default:
                return null;
        }
    }
}
