package com.example.inventariobp.controller;

import com.example.inventariobp.model.Product;
import com.example.inventariobp.model.dto.Response;
import com.example.inventariobp.model.Store;
import com.example.inventariobp.model.StoreProduct;
import com.example.inventariobp.model.dto.OrderDTO;
import com.example.inventariobp.service.interfaces.IStoreProductService;
import com.example.inventariobp.service.interfaces.IStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api("Apis that manage the stores")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/store")
public class StoreController {

    private final IStoreService storeService;
    private final IStoreProductService storeProductService;

    @ApiOperation("Find a store by ID")
    @GetMapping(value = "getStore/{id}")
    public ResponseEntity<Response> getStore(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Optional<Store> result = storeService.getStore(id);
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Find all stores")
    @GetMapping(value = "getAllStores")
    public ResponseEntity<Response> getAllStores() {
        Response response = new Response();
        try {
            List<Store> result = storeService.getAllStores();
            response.setMessage(String.valueOf(result.size()).concat(" Registros encontrados"));
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Create or update a store")
    @PostMapping(value = "saveStore", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<Response> saveStore(@RequestBody Store dto) {
        Response response = new Response();
        try {
            Store result = storeService.saveStore(dto);
            response.setAuto(result);
            response.setStatus(HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Delete a store")
    @DeleteMapping("deleteStore/{id}")
    public ResponseEntity<Response> deleteStore(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Long result = storeService.deleteStore(id);
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Apis to manage products in stores
     */

    @ApiOperation("Find a product assignment to the store by ID")
    @GetMapping(value = "store-product/getStoreProduct/{id}")
    public ResponseEntity<Response> getStoreProduct(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Optional<StoreProduct> result = storeProductService.getStoreProduct(id);
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Find all product assignments to the store")
    @GetMapping(value = "store-product/getAllProductsByStore/{storeId}")
    public ResponseEntity<Response> getAllProductsByStore(@PathVariable("storeId") Long storeId) {
        Response response = new Response();
        try {
            List<Product> result = storeProductService.getAllProductsByStore(storeId);
            response.setMessage(String.valueOf(result.size()).concat(" Registros encontrados"));
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Create or update a product assignment to the store")
    @PostMapping(value = "store-product/saveStoreProduct", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<Response> saveStoreProduct(@RequestBody StoreProduct dto) {
        Response response = new Response();
        try {
            StoreProduct result = storeProductService.saveStoreProduct(dto);
            response.setAuto(result);
            response.setStatus(HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Delete a product assignment to the store")
    @DeleteMapping("store-product/deleteStoreProduct/{id}")
    public ResponseEntity<Response> deleteStoreProduct(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Long result = storeProductService.deleteStoreProduct(id);
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Apis to manage orders
     */

    @ApiOperation("Place an order")
    @PostMapping(value = "orders/placeOrder", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<Response> placeOrder(@RequestBody OrderDTO data) {
        Response response = new Response();
        try {
            Boolean result = storeService.placeOrder(data);
            response.setAuto(result);
            response.setStatus(HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
