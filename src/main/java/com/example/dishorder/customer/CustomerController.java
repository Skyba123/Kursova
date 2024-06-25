package com.example.dishorder.customer;
import com.example.dishorder.customer.dto.RequestCustomer;
import com.example.dishorder.customer.dto.ResponseCustomer;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@Tag(name = "Customers", description = "Endpoints for Customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<ResponseCustomer> create(@RequestBody RequestCustomer data) {
        ResponseCustomer response = service.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseCustomer>> findAll() {
        List<ResponseCustomer> response = service.getAllCustomers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCustomer> findOne(@PathVariable Long id) {
        return  ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCustomer> update(@PathVariable Long id, @RequestBody RequestCustomer customer) {
        ResponseCustomer response = service.update(id, customer);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
