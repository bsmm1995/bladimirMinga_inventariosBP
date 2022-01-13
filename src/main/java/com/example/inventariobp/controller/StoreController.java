package com.example.inventariobp.controller;

import com.example.inventariobp.model.Response;
import com.example.inventariobp.model.StoreDTO;
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

    @ApiOperation("Find a store by ID")
    @GetMapping(value = "getStore/{id}")
    public ResponseEntity<Response> getStore(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Optional<StoreDTO> result = storeService.getStore(id);
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
            List<StoreDTO> result = storeService.getAllStores();
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
    public ResponseEntity<Response> saveStore(@RequestBody StoreDTO dto) {
        Response response = new Response();
        try {
            StoreDTO result = storeService.saveStore(dto);
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
}
