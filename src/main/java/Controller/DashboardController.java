package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashboardController {

    @FXML
    private Button btnCustomerForm;

    @FXML
    private Button btnItemForm;

    @FXML
    private Button btnOrderForm;

    @FXML
    private AnchorPane loadFormContain;

    @FXML
    void onActionBtnCustomerForm(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/View/CustomerForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);

        loadFormContain.getChildren().clear();
        loadFormContain.getChildren().add(load);

    }

    @FXML
    void onActionBtnItemForm(ActionEvent event) {

    }

    @FXML
    void onActionBtnOrderForm(ActionEvent event) {

    }

}
