package com.example.inventariobp.controller;

import com.example.inventariobp.data.Customer;
import com.example.inventariobp.data.dto.Response;
import com.example.inventariobp.service.interfaces.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api("Apis that manage the customers")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customer")
public class CustomerController {

    private final ICustomerService customerService;

    @ApiOperation("Find a customer by ID")
    @GetMapping(value = "getCustomer/{id}")
    public ResponseEntity<Response> getCustomer(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Optional<Customer> result = customerService.getCustomer(id);
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Find all customers")
    @GetMapping(value = "getAllCustomers")
    public ResponseEntity<Response> getAllCustomers() {
        Response response = new Response();
        try {
            List<Customer> result = customerService.getAllCustomers();
            response.setMessage(String.valueOf(result.size()).concat(" Registros encontrados"));
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Create or update a customer")
    @PostMapping(value = "saveCustomer", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<Response> saveCustomer(@RequestBody Customer dto) {
        Response response = new Response();
        try {
            Customer result = customerService.saveCustomer(dto);
            response.setAuto(result);
            response.setStatus(HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Delete a customer")
    @DeleteMapping("deleteCustomer/{id}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Long result = customerService.deleteCustomer(id);
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
