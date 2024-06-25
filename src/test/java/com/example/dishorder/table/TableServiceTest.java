package com.example.dishorder.table;

import com.example.dishorder.table.dto.RequestTable;
import com.example.dishorder.table.dto.ResponseTable;
import com.example.dishorder.table.entity.TableEntity;
import com.example.dishorder.table.entity.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TableServiceTest {

    @Mock
    private TableRepository repository;

    @InjectMocks
    private TableService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createTable_ValidRequest_Success() {
        // Arrange
        RequestTable requestTable = new RequestTable();
        requestTable.setNumber(1);
        requestTable.setSeats(4);
        requestTable.setOccupied(false);

        TableEntity savedEntity = new TableEntity(1L, 1, 4, false);
        when(repository.save(any(TableEntity.class))).thenReturn(savedEntity);

        // Act
        ResponseTable response = service.create(requestTable);

        // Assert
        assertNotNull(response);
        assertEquals(savedEntity.getId(), response.getId());
        assertEquals(savedEntity.getNumber(), response.getNumber());
        assertEquals(savedEntity.getSeats(), response.getSeats());
        assertEquals(savedEntity.isOccupied(), response.isOccupied());
    }

    @Test
    void getAllTables_ValidRequest_Success() {
        // Arrange
        List<TableEntity> entities = new ArrayList<>();
        entities.add(new TableEntity(1L, 1, 4, false));
        entities.add(new TableEntity(2L, 2, 6, true));
        when(repository.findAll()).thenReturn(entities);

        // Act
        List<ResponseTable> response = service.getAllTables();

        // Assert
        assertNotNull(response);
        assertEquals(entities.size(), response.size());
        assertEquals(entities.get(0).getId(), response.get(0).getId());
        assertEquals(entities.get(0).getNumber(), response.get(0).getNumber());
        assertEquals(entities.get(0).getSeats(), response.get(0).getSeats());
        assertEquals(entities.get(0).isOccupied(), response.get(0).isOccupied());
    }

    @Test
    void findOneTable_ValidId_Success() {
        // Arrange
        Long id = 1L;
        TableEntity entity = new TableEntity(id, 1, 4, false);
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        // Act
        ResponseTable response = service.findOne(id);

        // Assert
        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getNumber(), response.getNumber());
        assertEquals(entity.getSeats(), response.getSeats());
        assertEquals(entity.isOccupied(), response.isOccupied());
    }

    @Test
    void findOneTable_InvalidId_EntityNotFoundExceptionThrown() {
        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> service.findOne(id));
    }
}
