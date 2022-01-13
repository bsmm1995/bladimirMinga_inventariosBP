package com.example.inventariobp.controller;

import com.example.inventariobp.model.ProductDTO;
import com.example.inventariobp.model.Response;
import com.example.inventariobp.service.interfaces.IProductService;
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
@RequestMapping("/v1/product")
public class ProductController {

    private final IProductService productService;

    @ApiOperation("Find a product by ID")
    @GetMapping(value = "getProduct/{id}")
    public ResponseEntity<Response> getProduct(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Optional<ProductDTO> result = productService.getProduct(id);
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Find all products")
    @GetMapping(value = "getAllProducts")
    public ResponseEntity<Response> getAllProducts() {
        Response response = new Response();
        try {
            List<ProductDTO> result = productService.getAllProducts();
            response.setMessage(String.valueOf(result.size()).concat(" Registros encontrados"));
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Create or update a product")
    @PostMapping(value = "saveProduct", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<Response> saveProduct(@RequestBody ProductDTO dto) {
        Response response = new Response();
        try {
            ProductDTO result = productService.saveProduct(dto);
            response.setAuto(result);
            response.setStatus(HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Delete a product")
    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Long result = productService.deleteProduct(id);
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
