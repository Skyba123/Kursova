package com.example.dishorder.order;

import com.example.dishorder.order.OrderService;
import com.example.dishorder.order.dto.RequestOrder;
import com.example.dishorder.order.dto.ResponseOrder;
import com.example.dishorder.order.entity.OrderEntity;
import com.example.dishorder.order.entity.OrderRepository;
import com.example.dishorder.table.entity.TableEntity;
import com.example.dishorder.table.entity.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private TableRepository tableRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createOrder_ValidRequestWithTable_Success() {
        // Arrange
        RequestOrder requestOrder = new RequestOrder();
        requestOrder.setCompleted(false);
        requestOrder.setTableId(1L); // Assuming a valid table ID

        TableEntity tableEntity = new TableEntity();
        when(tableRepository.findById(1L)).thenReturn(Optional.of(tableEntity));

        OrderEntity savedEntity = new OrderEntity();
        savedEntity.setId(1L);
        savedEntity.setCompleted(false);
        savedEntity.setCreatedAt(new Date());
        savedEntity.setTable(tableEntity);
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(savedEntity);

        // Act
        ResponseOrder response = orderService.create(requestOrder);

        // Assert
        assertNotNull(response);
        assertEquals(savedEntity.getId(), response.getId());
        assertEquals(savedEntity.isCompleted(), response.isCompleted());
        assertEquals(savedEntity.getCreatedAt(), response.getCreatedAt());
        assertEquals(savedEntity.getTable(), tableEntity);
    }

    @Test
    void getAllOrders_ValidRequest_Success() {
        // Arrange
        List<OrderEntity> orders = new ArrayList<>();
        orders.add(new OrderEntity(1L, false, new Date(), null));
        orders.add(new OrderEntity(2L, true, new Date(), null));
        when(orderRepository.findAll()).thenReturn(orders);

        // Act
        List<ResponseOrder> response = orderService.getAllOrders();

        // Assert
        assertNotNull(response);
        assertEquals(orders.size(), response.size());
        assertEquals(orders.get(0).getId(), response.get(0).getId());
        assertEquals(orders.get(0).isCompleted(), response.get(0).isCompleted());
        assertEquals(orders.get(0).getCreatedAt(), response.get(0).getCreatedAt());
    }

    @Test
    void findOne_ValidId_Success() {
        // Arrange
        Long id = 1L;
        OrderEntity entity = new OrderEntity(id, false, new Date(), null);
        when(orderRepository.findById(id)).thenReturn(Optional.of(entity));

        // Act
        ResponseOrder response = orderService.findOne(id);

        // Assert
        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.isCompleted(), response.isCompleted());
        assertEquals(entity.getCreatedAt(), response.getCreatedAt());
    }

    @Test
    void findOne_InvalidId_EntityNotFoundExceptionThrown() {
        // Arrange
        Long id = 1L;
        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> orderService.findOne(id));
    }

    @Test
    void updateOrder_ValidRequestWithTable_Success() {
        // Arrange
        Long id = 1L;
        RequestOrder requestOrder = new RequestOrder();
        requestOrder.setCompleted(true);
        requestOrder.setTableId(1L); // Assuming a valid table ID

        TableEntity tableEntity = new TableEntity();
        when(tableRepository.findById(1L)).thenReturn(Optional.of(tableEntity));

        OrderEntity existingEntity = new OrderEntity(id, false, new Date(), null);
        when(orderRepository.findById(id)).thenReturn(Optional.of(existingEntity));
        OrderEntity savedEntity = new OrderEntity(id, true, existingEntity.getCreatedAt(), tableEntity);
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(savedEntity);

        // Act
        ResponseOrder response = orderService.update(id, requestOrder);

        // Assert
        assertNotNull(response);
        assertEquals(existingEntity.getId(), response.getId());
        assertEquals(requestOrder.isCompleted(), response.isCompleted());
        assertEquals(existingEntity.getCreatedAt(), response.getCreatedAt());
    }

    @Test
    void deleteOrder_ValidId_Success() {
        // Arrange
        Long id = 1L;

        // Act
        Long deletedId = orderService.deleteById(id);

        // Assert
        assertEquals(id, deletedId);
        verify(orderRepository, times(1)).deleteById(id);
    }
}
