package Controller.Order;

import DB.DBConnection;
import Model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {


    public boolean addOrderDetail(List<OrderDetail> orderDetails)  {

        for(OrderDetail orderDetail : orderDetails){

            boolean isAddOrderDetails = addOrderDetail(orderDetail);

          if(!isAddOrderDetails){
              return  false;
          }

        }
        return true;
    }

    public boolean addOrderDetail(OrderDetail orderDetail){

        String SQL = "Insert into orderdetail values(?,?,?,?)";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1,orderDetail.getOrderId());
            pstm.setObject(2,orderDetail.getItemCode());
            pstm.setObject(3,orderDetail.getQty());
            pstm.setObject(4,orderDetail.getUnitPrice());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
