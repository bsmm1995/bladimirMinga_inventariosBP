package com.example.inventariobp.controller;

import com.example.inventariobp.model.Customer;
import com.example.inventariobp.model.Transaction;
import com.example.inventariobp.model.TransactionDetail;
import com.example.inventariobp.model.dto.ReportDetailDTO;
import com.example.inventariobp.model.dto.Response;
import com.example.inventariobp.service.interfaces.ITransactionDetailService;
import com.example.inventariobp.service.interfaces.ITransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
            Optional<Transaction> result = transactionService.getTransaction(id);
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
            List<Transaction> result = transactionService.getAllTransactions();
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
    public ResponseEntity<Response> saveTransaction(@RequestBody Transaction dto) {
        Response response = new Response();
        try {
            Transaction result = transactionService.saveTransaction(dto);
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
            Optional<TransactionDetail> result = transactionDetailService.getTransactionDetail(id);
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
            List<TransactionDetail> result = transactionDetailService.getAllTransactionsDetail();
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
    public ResponseEntity<Response> saveTransactionDetail(@RequestBody TransactionDetail dto) {
        Response response = new Response();
        try {
            TransactionDetail result = transactionDetailService.saveTransactionDetail(dto);
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

    /**
     * Endpoints to get data for reports
     */

    @ApiOperation("Generate CSV report")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clienteDNI", value = "Customer's DNI", required = true, dataType = "String", example = "1100124567"),
            @ApiImplicitParam(name = "startDate", value = "Start date of the report", required = true, dataType = "Date", example = "2022-01-01 00:00:00"),
            @ApiImplicitParam(name = "endDate", value = "End date of the report", required = true, dataType = "Date", example = "2022-02-01 23:59:59")
    })
    @GetMapping(value = "reports/generateCSVReport")
    public ResponseEntity generateCSVReport(@RequestParam String clienteDNI, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
        HttpHeaders headers = new HttpHeaders();
        ByteArrayInputStream byteArrayOutputStream = null;
        String[] csvHeader = {"Transaction ID", "Date", "Store", "Product code", "Product name", "Price", "Quantity", "Total"};
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withRecordSeparator("\n"));
        ) {
            Map<String, Object> result = transactionService.getDataForCSVReport(clienteDNI, startDate, endDate);
            csvPrinter.printRecord("DNI:", clienteDNI);
            csvPrinter.printRecord("Customer:", ((Customer) result.get("customer")).getName());
            csvPrinter.printRecord("Date range:", startDate.toString().concat(" to ").concat(endDate.toString()));
            csvPrinter.printRecord();
            csvPrinter.printRecord(csvHeader);
            for (ReportDetailDTO row : (List<ReportDetailDTO>) result.get("detail"))
                csvPrinter.printRecord(row.getTransactionId(), new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(row.getDate()), row.getStore(), row.getProductCode(), row.getProductName(), row.getPrice(), row.getQuantity(), row.getTotal());
            csvPrinter.flush();
            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
            csvPrinter.flush();
        } catch (Exception e) {
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), true, null), new HttpHeaders(), HttpStatus.OK);
        }

        InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);
        String csvFileName = "Transactions-report-".concat(new Date().toString()).concat(".csv");
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(fileInputStream, headers, HttpStatus.OK);
    }

    @ApiOperation("Report of numbers of transactions group by store and date")
    @GetMapping(value = "reports/getNumberOfTransactionsGroupStoreAndDate")
    public ResponseEntity getNumberOfTransactionsGroupStoreAndDate() {
        Response response = new Response();
        try {
            List<Map<String, Object>> result = transactionService.getNumberOfTransactionsGroupStoreAndDate();
            response.setMessage(String.valueOf(result.size()).concat(" Registros encontrados"));
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation("Report of numbers of transactions group by store and product")
    @GetMapping(value = "reports/getSoldByStoreAndProduct")
    public ResponseEntity getSoldByStoreAndProduct() {
        Response response = new Response();
        try {
            List<Map<String, Object>> result = transactionService.getSoldByStoreAndProduct();
            response.setMessage(String.valueOf(result.size()).concat(" Registros encontrados"));
            response.setAuto(result);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
