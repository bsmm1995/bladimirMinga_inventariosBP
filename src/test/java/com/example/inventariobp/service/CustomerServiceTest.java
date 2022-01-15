package com.example.inventariobp.service;

import com.example.inventariobp.model.CustomerDTO;
import com.example.inventariobp.service.interfaces.ICustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {

    @MockBean
    ICustomerService customerServiceMock;

    @Test
    void getCustomerByDNI() {
        CustomerDTO customer = new CustomerDTO(1L, "Bladimir", "1900875327", "");

        when(customerServiceMock.getCustomerByDNI("1900875327")).thenReturn(customer);
        assertEquals(customer, customerServiceMock.getCustomerByDNI("1900875327"));

        when(customerServiceMock.getCustomerByDNI("1900875320")).thenReturn(null);
        assertNotEquals(customer, customerServiceMock.getCustomerByDNI("1900875320"));
    }
}