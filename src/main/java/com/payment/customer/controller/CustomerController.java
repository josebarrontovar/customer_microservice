package com.payment.customer.controller;

import com.payment.customer.entities.Customer;
import com.payment.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping()
    public List<Customer> getFindAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getFindById(@PathVariable Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public ResponseEntity<?> postCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> existCustomer = customerRepository.findById(id);
        if (existCustomer.isPresent()) {
            Customer newCustomer = existCustomer.get();
            newCustomer.setName(customer.getName());
            newCustomer.setPhoneNumber(customer.getPhoneNumber());
            Customer saveCustomer = customerRepository.save(newCustomer);
            return ResponseEntity.ok(saveCustomer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> existCustomer = customerRepository.findById(id);
        if (existCustomer.isPresent()) {
            customerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
