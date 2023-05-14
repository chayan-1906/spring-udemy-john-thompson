package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICustomerService {

    List<CustomerDTO> getAllCustomers();

    Optional<CustomerDTO> getCustomerById(UUID id);

    CustomerDTO saveCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateCustomer(UUID id, CustomerDTO customer);

    Boolean deleteCustomer(UUID id);

    Optional<CustomerDTO> patchCustomer(UUID id, CustomerDTO customer);
}
