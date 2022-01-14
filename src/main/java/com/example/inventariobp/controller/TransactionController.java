package com.example.inventariobp.controller;

import com.example.inventariobp.model.Response;
import com.example.inventariobp.model.TransactionDTO;
import com.example.inventariobp.model.TransactionDetailDTO;
import com.example.inventariobp.service.interfaces.ITransactionDetailService;
import com.example.inventariobp.service.interfaces.ITransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api("Apis that manage the transactions")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/transaction")
public class TransactionController {

    private final ITransactionService transactionService;
    private final ITransactionDetailService transactionDetailService;

    @ApiOperation("Find a transaction by ID")
    @GetMapping(value = "getTransaction/{id}")
    public ResponseEntity<Response> getTransaction(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Optional<TransactionDTO> result = transactionService.getTransaction(id);
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Find all transactions")
    @GetMapping(value = "getAllTransactions")
    public ResponseEntity<Response> getAllTransactions() {
        Response response = new Response();
        try {
            List<TransactionDTO> result = transactionService.getAllTransactions();
            response.setMessage(String.valueOf(result.size()).concat(" Registros encontrados"));
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Create or update a transaction")
    @PostMapping(value = "saveTransaction", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<Response> saveTransaction(@RequestBody TransactionDTO dto) {
        Response response = new Response();
        try {
            TransactionDTO result = transactionService.saveTransaction(dto);
            response.setAuto(result);
            response.setStatus(HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Delete a transaction")
    @DeleteMapping("deleteTransaction/{id}")
    public ResponseEntity<Response> deleteTransaction(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Long result = transactionService.deleteTransaction(id);
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Endpoints to manage transaction details
     */

    @ApiOperation("Find a transaction detail by ID")
    @GetMapping(value = "detail/getTransactionDetail/{id}")
    public ResponseEntity<Response> getTransactionDetail(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Optional<TransactionDetailDTO> result = transactionDetailService.getTransactionDetail(id);
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Find all transactions detail")
    @GetMapping(value = "detail/getAllTransactionsDetail")
    public ResponseEntity<Response> getAllTransactionsDetail() {
        Response response = new Response();
        try {
            List<TransactionDetailDTO> result = transactionDetailService.getAllTransactionsDetail();
            response.setMessage(String.valueOf(result.size()).concat(" Registros encontrados"));
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Create or update a transaction detail")
    @PostMapping(value = "detail/saveTransactionDetail", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<Response> saveTransactionDetail(@RequestBody TransactionDetailDTO dto) {
        Response response = new Response();
        try {
            TransactionDetailDTO result = transactionDetailService.saveTransactionDetail(dto);
            response.setAuto(result);
            response.setStatus(HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Delete a transaction detail")
    @DeleteMapping("detail/deleteTransactionDetail/{id}")
    public ResponseEntity<Response> deleteTransactionDetail(@PathVariable("id") Long id) {
        Response response = new Response();
        try {
            Long result = transactionDetailService.deleteTransactionDetail(id);
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
