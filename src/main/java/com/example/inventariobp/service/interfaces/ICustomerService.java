package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.data.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ICustomerService {

    Optional<Customer> getCustomer(Long id);

    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer dto);

    Long deleteCustomer(Long id);
}
