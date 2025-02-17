package Controller;

import DB.DBConnection;
import Model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService {
    @Override
    public boolean addCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public Customer searchCustomer(String id) {
        return null;
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
