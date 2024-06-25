package com.example.dishorder.customer;

import com.example.dishorder.customer.dto.RequestCustomer;
import com.example.dishorder.customer.dto.ResponseCustomer;
import com.example.dishorder.customer.entity.CustomerEntity;
import com.example.dishorder.customer.entity.CustomerRepository;
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

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllCustomers_ValidRequest_Success() {
        // Arrange
        List<CustomerEntity> customers = new ArrayList<>();
        customers.add(new CustomerEntity(1L, "John Doe", "1234567890"));
        customers.add(new CustomerEntity(2L, "Jane Smith", "9876543210"));
        when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<ResponseCustomer> response = customerService.getAllCustomers();

        // Assert
        assertNotNull(response);
        assertEquals(customers.size(), response.size());
        assertEquals(customers.get(0).getId(), response.get(0).getId());
        assertEquals(customers.get(0).getName(), response.get(0).getName());
        assertEquals(customers.get(0).getPhone(), response.get(0).getPhone());
    }

    @Test
    void findOne_ValidId_Success() {
        // Arrange
        Long id = 1L;
        CustomerEntity customerEntity = new CustomerEntity(id, "John Doe", "1234567890");
        when(customerRepository.findById(id)).thenReturn(Optional.of(customerEntity));

        // Act
        ResponseCustomer response = customerService.findOne(id);

        // Assert
        assertNotNull(response);
        assertEquals(customerEntity.getId(), response.getId());
        assertEquals(customerEntity.getName(), response.getName());
        assertEquals(customerEntity.getPhone(), response.getPhone());
    }

    @Test
    void create_ValidRequest_Success() {
        // Arrange
        RequestCustomer requestCustomer = new RequestCustomer();
        requestCustomer.setName("John Doe");
        requestCustomer.setPhone("1234567890");

        CustomerEntity savedEntity = new CustomerEntity(1L, "John Doe", "1234567890");
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(savedEntity);

        // Act
        ResponseCustomer response = customerService.create(requestCustomer);

        // Assert
        assertNotNull(response);
        assertEquals(savedEntity.getId(), response.getId());
        assertEquals(savedEntity.getName(), response.getName());
        assertEquals(savedEntity.getPhone(), response.getPhone());
    }

    @Test
    void update_ValidIdAndRequest_Success() {
        // Arrange
        Long id = 1L;
        RequestCustomer requestCustomer = new RequestCustomer();
        requestCustomer.setName("John Doe");
        requestCustomer.setPhone("1234567890");

        CustomerEntity existingEntity = new CustomerEntity(id, "Jane Smith", "9876543210");
        when(customerRepository.findById(id)).thenReturn(Optional.of(existingEntity));

        CustomerEntity updatedEntity = new CustomerEntity(id, "John Doe", "1234567890");
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(updatedEntity);

        // Act
        ResponseCustomer response = customerService.update(id, requestCustomer);

        // Assert
        assertNotNull(response);
        assertEquals(updatedEntity.getId(), response.getId());
        assertEquals(updatedEntity.getName(), response.getName());
        assertEquals(updatedEntity.getPhone(), response.getPhone());
    }
}
