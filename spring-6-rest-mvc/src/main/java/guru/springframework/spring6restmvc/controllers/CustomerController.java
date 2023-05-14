package guru.springframework.spring6restmvc.controllers;

import guru.springframework.spring6restmvc.models.CustomerDTO;
import guru.springframework.spring6restmvc.services.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<?> getCustomerById(@RequestParam UUID id) {
        return new ResponseEntity<>(customerService.getCustomerById(id).orElseThrow(NotFoundException::new), HttpStatus.OK);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customer) {
        CustomerDTO savedCustomer = customerService.saveCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer?id=" + savedCustomer.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customer, @RequestParam UUID id) {
        Optional<CustomerDTO> updatedCustomer = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<?> deleteCustomer(@RequestParam UUID id) {
        if (!customerService.deleteCustomer(id)) {
            throw new NotFoundException();
        }
        return new ResponseEntity<>("Beer with " + id + " deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("/patchCustomer")
    public ResponseEntity<?> updateCustomerPatchById(@RequestBody CustomerDTO customer, @RequestParam UUID id) {
        Optional<CustomerDTO> patchedCustomer = customerService.patchCustomer(id, customer);
        return new ResponseEntity<>(patchedCustomer, HttpStatus.OK);
    }
}
