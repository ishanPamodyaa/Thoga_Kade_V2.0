package Service.custom.impl;

import DB.DBConnection;
import Model.Customer;
import Service.custom.CustomerBo;
import Utill.DaoType;
import repocitory.DaoFactory;
import repocitory.custom.CustomerDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo{

    CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);

    @Override
    public boolean addCustomer(Customer customer) {
      return   customerDao.save(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public Customer searchCustomer(String id) {


        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement=  connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer where id="+"'"+id+"'");



            resultSet.next();
            return  new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Customer> getAll() {

        ArrayList<Customer> customerArrayList = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement=  connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer");


            while( resultSet.next()){
                System.out.println(resultSet.getString(1));
                Customer customer = new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                );

                customerArrayList.add(customer);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerArrayList;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }
}