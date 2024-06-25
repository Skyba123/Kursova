package com.example.dishorder.customer;

import com.example.dishorder.customer.dto.RequestCustomer;
import com.example.dishorder.customer.dto.ResponseCustomer;
import com.example.dishorder.customer.entity.CustomerEntity;
import com.example.dishorder.customer.entity.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public ResponseCustomer create(RequestCustomer customer) {
        CustomerEntity entity = new CustomerEntity(customer.getName(), customer.getPhone());
        return ResponseCustomer.toModel(repository.save(entity));
    }

    public List<ResponseCustomer> getAllCustomers() {
        List<CustomerEntity> entities = repository.findAll();
        return entities.stream()
                .map(ResponseCustomer::toModel)
                .collect(Collectors.toList());
    }

    public ResponseCustomer findOne(Long id) {
        CustomerEntity entity = repository.findById(id).orElse(null);
        if (entity == null) throw new EntityNotFoundException("Customer not found");
        return ResponseCustomer.toModel(entity);
    }

    public ResponseCustomer update(Long id, RequestCustomer requestCustomer) {
        CustomerEntity entity = repository.findById(id).orElse(null);
        if (entity == null) throw new EntityNotFoundException("Customer not found");

        entity.setName(requestCustomer.getName());
        entity.setPhone(requestCustomer.getPhone());
        return ResponseCustomer.toModel(repository.save(entity));
    }

    public Long deleteById(Long id) {
        repository.deleteById(id);
        return id;
    }
}
