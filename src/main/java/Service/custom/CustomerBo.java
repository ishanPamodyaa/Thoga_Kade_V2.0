package Service.custom;

import Model.Customer;
import Service.SuperService;

import java.util.List;

public interface CustomerBo extends SuperService {
        boolean addCustomer(Customer customer);

        boolean updateCustomer(Customer customer);
        Customer searchCustomer (String id);
        List<Customer> getAll();
        boolean deleteCustomer(String id);


}
