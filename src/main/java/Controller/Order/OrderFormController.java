package Controller.Order;

import Controller.item.itemController;
import Model.Customer;
import Model.Item;
import Model.Order;
import Model.OrderDetail;
import Model.TM.CartTM;
import Service.BoFactory;
import Service.custom.CustomerBo;
import Utill.BoType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    public TextField txtOrderId;
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
    ObservableList <CartTM> cartItems = FXCollections.observableArrayList();

    CustomerBo customerBo = BoFactory.getInstance().getBoType(BoType.CUSTOMER);

    @FXML
    void onActionBtnAddtoCart(ActionEvent event) {



        String code = cmbItemCode.getValue().toString();
        String description = txtDiscription.getText();
        Integer qty = Integer.parseInt(txtQty.getText());
        Double price =Double.parseDouble(txtUnitPrice.getText());
        Double totalPrice = qty*price;

        cartItems.add(new CartTM(code,description,qty,price,totalPrice));




        tblOrder.setItems(cartItems);
        calNetTotal();

    }

    @FXML
    void onActionPlaceOrder(ActionEvent event) throws SQLException {

        String orderId = txtOrderId.getText();
        String date = txtDate.getText();
        String customerId = cmbCustID.getValue().toString();

        List <OrderDetail> orderDetails = new ArrayList<>();

        cartItems.forEach(cartTM -> {
           orderDetails.add(
            new OrderDetail(
              orderId,
              cartTM.getItemCode(),
              cartTM.getQtyOnHand(),
              cartTM.getUnitPrice()
            )
            );
        });

       Order order= new Order(
                orderId,date,customerId,orderDetails
        );


       if(new OrderController().placrOrder(order)){
           new Alert(Alert.AlertType.INFORMATION,"Order Place !").show();
       }else {
           new Alert(Alert.AlertType.ERROR,"Order Not Place !").show();
       }
    }

    private void setDateTime(){
        Date date = new Date();
       SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
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

    private  void calNetTotal(){
        Double netTotal=0.0;

        for(CartTM tm: cartItems){

            netTotal += tm.getTotal();
        }

        txtTotalPrice.setText(netTotal.toString());
    }

    private void loadCustomerID(){
//        CustomerController customerController = new CustomerController();


        ObservableList <String> custID = FXCollections.observableArrayList();
        List<Customer> all = customerBo.getAll();
        all.forEach(customer -> {
            custID.add(customer.getId());
        });
        cmbCustID.setItems(custID);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDesc.setCellValueFactory(new  PropertyValueFactory<>("discription"));
        txtQIH.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        setDateTime();
        loadCustomerID();
        loadItemCode();


        cmbCustID.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) ->{
//            System.out.println(o +"  "+ t1);
            if(newVal!=null){
                searchCustomerData(newVal.toString());

            }

        } );

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal,newVal ) -> {
            if(newVal!=null){
                searchItemData(newVal.toString());
            }
        } );
    }

    private void loadItemCode() {

        cmbItemCode.setItems(new itemController().getCode());
    }

    private void searchItemData(String itemCode) {
   Item item = new itemController().searchItem(itemCode);

        System.out.println(item);

        txtDiscription.setText(item.getDescription());
        txtStock.setText(item.getQtyOnHand().toString());
        txtUnitPrice.setText(item.getUnitPrice().toString());


    }

    private void searchCustomerData(String cmbCustID) {
//       Customer customer =  new CustomerController().searchCustomer(cmbCustID);
        Customer customer = customerBo.searchCustomer(cmbCustID);
        System.out.println(customer);

        txtCustName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
    }


}
