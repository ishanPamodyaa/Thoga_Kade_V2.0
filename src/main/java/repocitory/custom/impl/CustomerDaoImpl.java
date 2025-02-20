package repocitory.custom.impl;

import DB.DBConnection;
import Model.Customer;
import repocitory.custom.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer entity) {
        String SQL = "insert into customer values(?,?,?,?)";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1,entity.getId());
            pstm.setObject(2,entity.getName());
            pstm.setObject(3,entity.getAddress());
            pstm.setObject(4,entity.getSalary());

            return pstm.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(String s, Customer entity) {
        return false;
    }

    @Override
    public Customer search(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<Customer> getAll() {
        return List.of();
    }
}
