package com.example.dishorder.order;

import com.example.dishorder.order.dto.RequestOrder;
import com.example.dishorder.order.dto.ResponseOrder;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@Tag(name = "Order", description = "Endpoints for Order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<ResponseOrder> create(@RequestBody RequestOrder data) {
        ResponseOrder response = service.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseOrder>> findAll() {
        List<ResponseOrder> response = service.getAllOrders();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrder> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseOrder> update(@PathVariable Long id, @RequestBody RequestOrder data) {
        ResponseOrder response = service.update(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
