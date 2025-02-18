package Controller;

import DB.DBConnection;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink linkRegForm;

    @FXML
    private PasswordField pswTxt;

    @FXML
    private TextField txtEmail;

    @FXML
    void onActionBtnLogin(ActionEvent event) throws SQLException, IOException {

        String key = "123#ish" ;

        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

        basicTextEncryptor.setPassword(key);


        String SQL = "Select * from users where email="+ "'"+txtEmail.getText()+"'";
        Connection conection = DBConnection.getInstance().getConnection();
        ResultSet resultset = conection.createStatement().executeQuery(SQL);
//        System.out.println(resultset);
//        System.out.println(txtEmail.getText()+ "  "+ pswTxt.getText() );
        if(resultset.next()){
           User user= new User(
                    resultset.getString(2),
                    resultset.getString(3),
                    resultset.getString(4)
            );

           if(basicTextEncryptor.decrypt(user.getPassword()).equals(pswTxt.getText())){
               Stage stage = new Stage();
               stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"))));
                stage.show();
           }else {
               new Alert(Alert.AlertType.ERROR,"Check Your Password").show();
           }
            System.out.println(user);
        }else {
          new Alert(Alert.AlertType.ERROR,"User Not Found").show();
        }

    }

    @FXML
    void onActionLinkToRegForm(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/CustomerRegisterForm.fxml"))));
        stage.show();
    }

}
