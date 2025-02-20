package Controller;

import DB.DBConnection;
import Model.Customer;
import Model.TM.CartTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

public class CustomerFormController {

    @FXML
    private TableColumn ColAddress;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnDeleteCustomer1;

    @FXML
    private Button btnReload;

    @FXML
    private Button btnSearchCustomer;

    @FXML
    private Button btnUpdateCustomer;

    @FXML
    private TableColumn colID;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView tblCustDetail;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    void btnOnActionAddCustomer(ActionEvent event) {
       boolean isCustomerAdd =  new CustomerController().addCustomer( new Customer(
                txtID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        ));

       if(isCustomerAdd){
           new Alert(Alert.AlertType.INFORMATION,"Customer Added !");
       }else {
           new Alert(Alert.AlertType.INFORMATION,"Customer Not Added !");
       }
    }

    @FXML
    void btnOnActionDeleteCustomer(ActionEvent event) {

    }

    @FXML
    void btnOnActionReload(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnOnActionSearchCustomer(ActionEvent event) {

    }

    @FXML
    void btnOnActionUpaterCustomer(ActionEvent event) {

    }

    private void loadTable(){

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));


        ObservableList <Customer> customerObservableList = FXCollections.observableArrayList();

        new CustomerController().getAll().forEach(customer ->{
            customerObservableList.add(customer);
        });


        tblCustDetail.setItems(customerObservableList);
    }

}
