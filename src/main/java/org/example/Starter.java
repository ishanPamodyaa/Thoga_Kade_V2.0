package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

public class Starter extends Application {
    public static void main(String[] args) {

        String password = "ishan123";
        String key = "123#ish" ;

        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

        basicTextEncryptor.setPassword(key);

        String encrypt = basicTextEncryptor.encrypt(password);
        System.out.println("encript pw"+encrypt );

        String decrypt = basicTextEncryptor.decrypt(encrypt);

        System.out.println("decrypt pw" + decrypt);


        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/LoginForm.fxml"))));
        stage.show();
    }
}
