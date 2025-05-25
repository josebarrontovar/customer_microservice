package com.payment.customer.Service;

import com.payment.customer.dao.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customer);
    List<CustomerDTO> getAllCustomer();
}
