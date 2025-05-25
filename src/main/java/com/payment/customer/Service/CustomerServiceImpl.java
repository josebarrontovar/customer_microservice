package com.payment.customer.Service;

import com.payment.customer.dao.CustomerDTO;
import com.payment.customer.entities.Customer;
import com.payment.customer.exceptions.CustomerException;
import com.payment.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapperConfig;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerdto) {
        try {
            Customer customer = modelMapperConfig.map(customerdto, Customer.class);
            customerRepository.save(customer);
            return modelMapperConfig.map(customer, CustomerDTO.class);
        } catch (Exception ex) {
            throw  new CustomerException("dd"+ex.getMessage());
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> listCustomer = customerRepository.findAll();
        return listCustomer.stream().map(customer -> modelMapperConfig.map(customer, CustomerDTO.class)).toList();
    }
}