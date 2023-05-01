package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.Beer;
import guru.springframework.spring6restmvc.models.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Customer 1")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Customer 2")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer.builder()
                .id(UUID.randomUUID())
                .customerName("Customer 3")
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Customer getCustomerById(UUID id) {
        return customerMap.get(id);
    }

    /**
     * @return
     */
    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    /**
     * @param customer
     * @return
     */
    @Override
    public Customer saveCustomer(Customer customer) {
        Customer savedCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        customerMap.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }

    /**
     * @param id
     * @param customer
     * @return
     */
    @Override
    public Customer updateCustomer(UUID id, Customer customer) {
        Customer existingCustomer = customerMap.get(id);
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setVersion(customer.getVersion());
        existingCustomer.setLastModifiedDate(LocalDateTime.now());
        customerMap.put(existingCustomer.getId(), existingCustomer);
        return existingCustomer;
    }

    /**
     * @param id
     */
    @Override
    public void deleteCustomer(UUID id) {
        customerMap.remove(id);
    }

    /**
     * @param id
     * @param customer
     * @return
     */
    @Override
    public Customer patchCustomer(UUID id, Customer customer) {
        Customer existing = customerMap.get(id);
        if (StringUtils.hasText(customer.getCustomerName())) {
            existing.setCustomerName(customer.getCustomerName());
        }
        return existing;
    }
}
