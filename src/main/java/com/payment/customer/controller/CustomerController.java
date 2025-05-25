package com.payment.customer.controller;

import com.payment.customer.Service.CustomerService;
import com.payment.customer.dao.CustomerDTO;
import com.payment.customer.entities.Customer;
import com.payment.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

   @PostMapping("/createCustomer")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
       return ResponseEntity.ok(customerService.createCustomer(customerDTO));
   }

   @GetMapping("/allCustomers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomer(){
       return ResponseEntity.ok(customerService.getAllCustomer());
   }
}
