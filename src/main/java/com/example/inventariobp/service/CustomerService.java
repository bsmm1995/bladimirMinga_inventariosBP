package com.example.inventariobp.service;

import com.example.inventariobp.model.CustomerDTO;
import com.example.inventariobp.repository.ICustomerRepository;
import com.example.inventariobp.service.interfaces.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private EntityManager entityManager;
    private final ICustomerRepository customerRepository;

    @Override
    public Optional<CustomerDTO> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public CustomerDTO getCustomerByDNI(String dni) {
        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerDTO> query = criteria.createQuery(CustomerDTO.class);
        Root<CustomerDTO> root = query.from(CustomerDTO.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteria.equal(root.get("dni"), dni));

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        try {
            CustomerDTO customer = entityManager.createQuery(query).getSingleResult();
            return customer;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO dto) {
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
