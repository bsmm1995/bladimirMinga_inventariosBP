package com.example.inventariobp.service;

import com.example.inventariobp.model.CustomerDTO;
import com.example.inventariobp.model.StoreDTO;
import com.example.inventariobp.repository.ICustomerRepository;
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
    public Optional<CustomerDTO> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO dto) {
        return customerRepository.save(dto);
    }

    @Override
    public Long deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return id;
    }
}
