package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ICustomerService {

    Optional<Customer> getCustomer(Long id);

    Optional<Customer> getCustomerByDNI(String dni);

    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer dto);

    Long deleteCustomer(Long id);
}
