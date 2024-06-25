package com.example.dishorder.table;

import com.example.dishorder.table.dto.RequestTable;
import com.example.dishorder.table.dto.ResponseTable;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tables")
@Tag(name = "Table", description = "Endpoints for Table")
public class TableController {

    @Autowired
    private TableService service;

    @PostMapping
    public ResponseEntity<ResponseTable> create(@RequestBody RequestTable data) {
        ResponseTable response = service.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseTable>> findAll() {
        List<ResponseTable> response = service.getAllTables();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTable> findOne(@PathVariable Long id) {
        return  ResponseEntity.ok(service.findOne(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseTable> update(@PathVariable Long id, @RequestBody RequestTable user) {
        ResponseTable response = service.update(id, user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
