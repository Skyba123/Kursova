package com.example.dishorder.dish;

import com.example.dishorder.dish.dto.RequestDish;
import com.example.dishorder.dish.dto.ResponseDish;
import com.example.dishorder.dish.entity.DishEntity;
import com.example.dishorder.dish.entity.DishRepository;
import com.example.dishorder.order.entity.OrderEntity;
import com.example.dishorder.order.entity.OrderRepository;
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

public class DishServiceTest {

    @Mock
    private DishRepository dishRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private DishService dishService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createDish_ValidRequestWithOrder_Success() {
        // Arrange
        RequestDish requestDish = new RequestDish();
        requestDish.setName("Test Dish");
        requestDish.setDescription("Test Description");
        requestDish.setPrice(10.0);
        requestDish.setOrderId(1L); // Assuming a valid order ID

        OrderEntity orderEntity = new OrderEntity();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));

        DishEntity savedEntity = new DishEntity();
        savedEntity.setId(1L);
        savedEntity.setName("Test Dish");
        savedEntity.setDescription("Test Description");
        savedEntity.setPrice(10.0);
        savedEntity.setOrder(orderEntity);
        when(dishRepository.save(any(DishEntity.class))).thenReturn(savedEntity);

        // Act
        ResponseDish response = dishService.create(requestDish);

        // Assert
        assertNotNull(response);
        assertEquals(savedEntity.getId(), response.getId());
        assertEquals(savedEntity.getName(), response.getName());
        assertEquals(savedEntity.getDescription(), response.getDescription());
        assertEquals(savedEntity.getPrice(), response.getPrice());
        assertEquals(savedEntity.getOrder(), orderEntity);
    }

    @Test
    void getAllDishes_ValidRequest_Success() {
        // Arrange
        List<DishEntity> dishes = new ArrayList<>();
        dishes.add(new DishEntity(1L, "Test Dish 1", "Description 1", 10.0, null));
        dishes.add(new DishEntity(2L, "Test Dish 2", "Description 2", 15.0, null));
        when(dishRepository.findAll()).thenReturn(dishes);

        // Act
        List<ResponseDish> response = dishService.getAllDishes();

        // Assert
        assertNotNull(response);
        assertEquals(dishes.size(), response.size());
        assertEquals(dishes.get(0).getId(), response.get(0).getId());
        assertEquals(dishes.get(0).getName(), response.get(0).getName());
        assertEquals(dishes.get(0).getDescription(), response.get(0).getDescription());
        assertEquals(dishes.get(0).getPrice(), response.get(0).getPrice());
    }

    @Test
    void findOne_ValidId_Success() {
        // Arrange
        Long id = 1L;
        DishEntity dishEntity = new DishEntity(id, "Test Dish", "Test Description", 10.0, null);
        when(dishRepository.findById(id)).thenReturn(Optional.of(dishEntity));

        // Act
        ResponseDish response = dishService.findOne(id);

        // Assert
        assertNotNull(response);
        assertEquals(dishEntity.getId(), response.getId());
        assertEquals(dishEntity.getName(), response.getName());
        assertEquals(dishEntity.getDescription(), response.getDescription());
        assertEquals(dishEntity.getPrice(), response.getPrice());
    }

    @Test
    void deleteById_ValidId_Success() {
        // Arrange
        Long id = 1L;
        DishEntity dishEntity = new DishEntity(id, "Test Dish", "Test Description", 10.0, null);
        when(dishRepository.findById(id)).thenReturn(Optional.of(dishEntity));

        // Act
        Long deletedId = dishService.deleteById(id);

        // Assert
        assertNotNull(deletedId);
        assertEquals(id, deletedId);
        verify(dishRepository, times(1)).deleteById(id);
    }
}
