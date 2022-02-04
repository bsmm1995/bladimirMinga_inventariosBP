package com.example.inventariobp.service;

import com.example.inventariobp.data.Customer;
import com.example.inventariobp.repository.interfaces.ICustomerRepository;
import com.example.inventariobp.service.interfaces.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty())
            throw new IllegalStateException("The customer's name is required");

        if (dto.getDni() == null || dto.getDni().trim().isEmpty())
            throw new IllegalStateException("Customer DNI is required");

        return customerRepository.save(dto);
    }

    @Override
    public Long deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return id;
    }
}
