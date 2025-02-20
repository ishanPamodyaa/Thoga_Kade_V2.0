package Controller.Order;

import Controller.item.itemController;
import DB.DBConnection;
import Model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {

    public  boolean placrOrder(Order order) throws SQLException {

        String SQL = "insert into orders values(?,?,?)";
        Connection connection =  DBConnection.getInstance().getConnection();
        try {

            connection.setAutoCommit(false);
            PreparedStatement pstm =  connection.prepareStatement(SQL);
            pstm.setObject(1,order.getId());
            pstm.setObject(2,order.getDate());
            pstm.setObject(3,order.getCustomerId());
            Boolean isOrderAdd = pstm.executeUpdate() > 0;

            if(isOrderAdd){
               boolean isOrderDetailsAdd = new OrderDetailController().addOrderDetail(order.getOrderDetails());
//               System.out.println(b);
                if (isOrderDetailsAdd){
                  boolean isUpdateStock = new itemController().updateStock(order.getOrderDetails());

                  if(isUpdateStock){
                      connection.commit();
                      return true;

                  }
                }
            }
        } finally {
            connection.setAutoCommit(true);
        }

     connection.rollback();
    return false;
    }
}
