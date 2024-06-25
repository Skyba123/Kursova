package com.example.dishorder.order;

import com.example.dishorder.dish.entity.DishEntity;
import com.example.dishorder.dish.entity.DishRepository;
import com.example.dishorder.order.dto.RequestOrder;
import com.example.dishorder.order.dto.ResponseOrder;
import com.example.dishorder.order.entity.OrderEntity;
import com.example.dishorder.order.entity.OrderRepository;
import com.example.dishorder.table.entity.TableEntity;
import com.example.dishorder.table.entity.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private TableRepository tableRepository;


    public ResponseOrder create(RequestOrder requestOrder) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCompleted(requestOrder.isCompleted());
        orderEntity.setCreatedAt(new Date());
        if (requestOrder.getTableId() != null) {
            TableEntity table = tableRepository.findById(requestOrder.getTableId())
                    .orElseThrow(() -> new EntityNotFoundException("Table not found"));
            orderEntity.setTable(table);
        }
        orderEntity = repository.save(orderEntity);

        return ResponseOrder.toModel(orderEntity);
    }

    public List<ResponseOrder> getAllOrders() {
        List<OrderEntity> orders = repository.findAll();
        return orders.stream()
                .map(ResponseOrder::toModel)
                .collect(Collectors.toList());
    }

    public ResponseOrder findOne(Long id) {
        OrderEntity orderEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return ResponseOrder.toModel(orderEntity);
    }

    public ResponseOrder update(Long id, RequestOrder requestOrder) {
        OrderEntity orderEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (orderEntity.getTable() != null) {
            TableEntity table = tableRepository.findById(requestOrder.getTableId())
                    .orElseThrow(() -> new EntityNotFoundException("Table not found"));
            orderEntity.setTable(table);
        }
        orderEntity.setCompleted(requestOrder.isCompleted());

        orderEntity = repository.save(orderEntity);

        return ResponseOrder.toModel(orderEntity);
    }

    public Long deleteById(Long id) {
        repository.deleteById(id);
        return id;
    }

}
