package com.example.inventariobp.service.interfaces;

import com.example.inventariobp.model.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    Optional<CustomerDTO> getCustomer(Long id);

    CustomerDTO getCustomerByDNI(String dni);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO saveCustomer(CustomerDTO dto);

    Long deleteCustomer(Long id);
}
