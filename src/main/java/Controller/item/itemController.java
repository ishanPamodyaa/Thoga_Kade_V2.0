package Controller.item;

import DB.DBConnection;
import Model.Item;
import Model.OrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class itemController implements itemService {

    @Override
    public boolean addItem() {
        return false;
    }

    @Override
    public boolean updateItem() {
        return false;
    }

    @Override
    public boolean deleteItem() {
        return false;
    }

    @Override
    public Item searchItem(String code) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("select * from item where code="+"'"+code+"'");

            resultSet.next();
            return  new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Item> getAll() {

        try {
            List<Item> itemList = new ArrayList<>();
           Connection connection =  DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("select * from item");

            while (resultSet.next()){
               itemList.add(
                new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                ));
            }
        return itemList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public ObservableList <String> getCode(){
       ObservableList<String> itemCode = FXCollections.observableArrayList();
       List<Item> itemList =getAll();


       itemList.forEach(item -> {
           itemCode.add(item.getCode());
       } );

       return itemCode;
    }

    public boolean updateStock (List<OrderDetail> orderDetails){
        for(OrderDetail orderDetail : orderDetails){
            boolean isUpdateStock = updateStock(orderDetail);
            if(!isUpdateStock){
                return false;
            }
        }
        return true;
    }

    public boolean updateStock(OrderDetail orderDetail){

        String SQL = "update item set qtyOnHand=qtyOnHand-? where code = ?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setObject(1,orderDetail.getQty());
            pstm.setObject(2,orderDetail.getItemCode());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
