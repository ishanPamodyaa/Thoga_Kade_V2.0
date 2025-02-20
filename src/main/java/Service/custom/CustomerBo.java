package Service.custom;

import Model.Customer;

import java.util.List;

public interface CustomerBo {





        boolean addCustomer(Customer customer);

        boolean updateCustomer(Customer customer);
        Customer searchCustomer (String id);
        List<Customer> getAll();
        boolean deleteCustomer(String id);


}
