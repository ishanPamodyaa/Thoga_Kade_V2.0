package Service.custom.impl;

import DB.DBConnection;
import Model.Customer;
import Service.custom.CustomerBo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo{

    @Override
    public boolean addCustomer(Customer customer) {
        String SQL = "insert into customer values(?,?,?,?)";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1,customer.getId());
            pstm.setObject(2,customer.getName());
            pstm.setObject(3,customer.getAddress());
            pstm.setObject(4,customer.getSalary());

            return pstm.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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