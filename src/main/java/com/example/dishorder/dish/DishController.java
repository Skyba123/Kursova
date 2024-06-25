package com.example.dishorder.dish;

import com.example.dishorder.dish.dto.RequestDish;
import com.example.dishorder.dish.dto.ResponseDish;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dishes")
@Tag(name = "Dishes", description = "Endpoints for Dishes")
public class DishController {

    @Autowired
    private DishService service;

    @PostMapping
    public ResponseEntity<ResponseDish> create(@RequestBody RequestDish data) {
        ResponseDish response = service.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDish>> findAll() {
        List<ResponseDish> response = service.getAllDishes();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDish> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDish> update(@PathVariable Long id, @RequestBody RequestDish data) {
        ResponseDish response = service.update(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
