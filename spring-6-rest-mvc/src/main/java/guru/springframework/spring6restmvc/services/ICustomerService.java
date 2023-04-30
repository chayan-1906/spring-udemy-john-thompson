package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.Customer;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {

    Customer getCustomerById(UUID id);

    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(UUID id, Customer customer);
}
