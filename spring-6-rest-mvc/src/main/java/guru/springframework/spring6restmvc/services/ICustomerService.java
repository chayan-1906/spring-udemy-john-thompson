package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(UUID id);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(UUID id, Customer customer);

    void deleteCustomer(UUID id);

    Customer patchCustomer(UUID id, Customer customer);
}
