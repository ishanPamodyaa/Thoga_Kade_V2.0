package Controller;

import Model.Customer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    private Button btnAddtoCart;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private ComboBox cmbCustID;

    @FXML
    private ComboBox cmbItemCode;

    @FXML
    private TableColumn colDesc;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableView tblOrder;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustName;

    @FXML
    private Label txtDate;

    @FXML
    private TextField txtDiscription;

    @FXML
    private TableColumn txtQIH;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtStock;

    @FXML
    private Label txtTime;

    @FXML
    private Label txtTotalPrice;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void onActionBtnAddtoCart(ActionEvent event) {

    }

    @FXML
    void onActionPlaceOrder(ActionEvent event) {

    }

    private void setDateTime(){
        Date date = new Date();
       SimpleDateFormat dateFormat =  new SimpleDateFormat("dd-MM-yyyy");
       String format = dateFormat.format(date);
       txtDate.setText(format);

       Timeline timeline = new Timeline(
               new KeyFrame(Duration.ZERO,e ->{
                   LocalTime now = LocalTime.now();
                   txtTime.setText(now.getHour()+":"+now.getMinute()+":"+now.getSecond());
               }),
               new KeyFrame(Duration.seconds(1))
       );
       timeline.setCycleCount(Animation.INDEFINITE);
       timeline.play();

    }

    private void loadCustomerID(){
        CustomerController customerController = new CustomerController();


        ObservableList <String> custID = FXCollections.observableArrayList();
        List<Customer> all = customerController.getAll();
        all.forEach(customer -> {
            custID.add(customer.getId());
        });
        cmbCustID.setItems(custID);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDateTime();
        loadCustomerID();


        cmbCustID.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) ->{
//            System.out.println(o +"  "+ t1);
            if(newVal!=null){
                searchCustomerData(newVal.toString());

            }

        } );
    }

    private void searchCustomerData(String cmbCustID) {
       Customer customer =  new CustomerController().searchCustomer(cmbCustID);

        System.out.println(customer);

        txtCustName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
    }
}
