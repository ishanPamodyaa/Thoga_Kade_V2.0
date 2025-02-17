package Controller;

import DB.DBConnection;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRegisterFormController {

    @FXML
    private Button btnRegister;

    @FXML
    private PasswordField pswdConfirm;

    @FXML
    private PasswordField pswdField;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    void onActionBtnRegister(ActionEvent event) throws SQLException {

        String SQl = "Insert into users (username,email,password) values(?,?,?)";


        if(pswdField.getText().equals(pswdConfirm.getText())){

            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet=  connection.createStatement().executeQuery("select * from users where email="+"'"+txtEmail.getText()+"'");
            if(!resultSet.next()){
            System.out.println(resultSet);
            User user = new User(
                    txtName.getText(),
                    txtEmail.getText(),
                    pswdField.getText()
            );

            PreparedStatement pstm =  connection.prepareStatement(SQl);
            pstm.setString(1,user.getUsername());
                pstm.setString(2,user.getEmail());
                pstm.setString(3,user.getPassword());
                pstm.executeUpdate();

        }else {
                new Alert(Alert.AlertType.ERROR,"User Allrady Registerd").show();

        }}else {
            new Alert(Alert.AlertType.ERROR,"Password Not Matched").show();
        }

    }

}
