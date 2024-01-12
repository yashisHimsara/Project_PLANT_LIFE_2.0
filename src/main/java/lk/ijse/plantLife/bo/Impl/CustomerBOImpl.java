package lk.ijse.plantLife.bo.Impl;

import lk.ijse.plantLife.dao.CustomerDAO;
import lk.ijse.plantLife.dao.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.plantLife.dto.CustomerDto;
import lk.ijse.plantLife.entity.Customer;

public class CustomerBOImpl {
    CustomerDAO customerDAO =
            (CustomerDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.CUSTOMER);


    public ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers=customerDAO.getAll();
        ArrayList<CustomerDto> customerDTOS=new ArrayList<>();
        for (Customer customerDto:customers) {
            customerDTOS.add(new CustomerDto(customerDto.getId(), customerDto.getName(), customerDto.getAddress(), customerDto.getPhoneNum()));
        }
        return customerDTOS;
    }


    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException{
        //customer business logic example
        return customerDAO.save(new Customer(dto.getId(),dto.getName(),dto.getAddress(), dto.getPhoneNum()));
    }

    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getAddress(), dto.getPhoneNum()));
    }

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
       return customerDAO.delete(id);
    }
}
