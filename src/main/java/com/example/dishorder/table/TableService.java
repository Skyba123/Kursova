package com.example.dishorder.table;

import com.example.dishorder.table.dto.RequestTable;
import com.example.dishorder.table.dto.ResponseTable;
import com.example.dishorder.table.entity.TableEntity;
import com.example.dishorder.table.entity.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {
    @Autowired
    private TableRepository repository;

    public ResponseTable create(RequestTable table) {
        TableEntity entity = new TableEntity(table.getNumber(), table.getSeats(), table.isOccupied());
        return ResponseTable.toModel(repository.save(entity));
    }


    public List<ResponseTable> getAllTables() {
        List<TableEntity> entities = repository.findAll();
        return entities.stream()
                .map(ResponseTable::toModel)
                .collect(Collectors.toList());
    }

    public ResponseTable findOne(Long id) {
        TableEntity entity = repository.findById(id).orElse(null);
        if (entity == null) throw new EntityNotFoundException("Table not found");
        return ResponseTable.toModel(entity);
    }

    public ResponseTable update(Long id, RequestTable requestTable) {
        TableEntity entity = repository.findById(id).orElse(null);
        if (entity == null) throw new EntityNotFoundException("Table not found");

        entity.setNumber(requestTable.getNumber());
        entity.setOccupied(requestTable.isOccupied());
        entity.setSeats(requestTable.getSeats());
        return ResponseTable.toModel(repository.save(entity));
    }

    public Long deleteById(Long id) {
        repository.deleteById(id);
        return id;
    }

}
