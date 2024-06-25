package com.example.dishorder.dish;

import com.example.dishorder.dish.dto.RequestDish;
import com.example.dishorder.dish.dto.ResponseDish;
import com.example.dishorder.dish.entity.DishEntity;
import com.example.dishorder.dish.entity.DishRepository;
import com.example.dishorder.order.entity.OrderEntity;
import com.example.dishorder.order.entity.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishService {

    @Autowired
    private DishRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    public ResponseDish create(RequestDish requestDish) {
        DishEntity dishEntity = new DishEntity();
        dishEntity.setName(requestDish.getName());
        dishEntity.setDescription(requestDish.getDescription());
        dishEntity.setPrice(requestDish.getPrice());

        if (requestDish.getOrderId() != null) {
            OrderEntity orderEntity = orderRepository.findById(requestDish.getOrderId())
                    .orElseThrow(() -> new EntityNotFoundException("Order not found"));
            dishEntity.setOrder(orderEntity);
        }

        dishEntity = repository.save(dishEntity);

        return ResponseDish.toModel(dishEntity);
    }

    public List<ResponseDish> getAllDishes() {
        List<DishEntity> dishes = repository.findAll();
        return dishes.stream()
                .map(ResponseDish::toModel)
                .collect(Collectors.toList());
    }

    public ResponseDish findOne(Long id) {
        DishEntity dishEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dish not found"));
        return ResponseDish.toModel(dishEntity);
    }

    public ResponseDish update(Long id, RequestDish requestDish) {
        DishEntity dishEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dish not found"));

        dishEntity.setName(requestDish.getName());
        dishEntity.setDescription(requestDish.getDescription());
        dishEntity.setPrice(requestDish.getPrice());

        if (requestDish.getOrderId() != null) {
            OrderEntity orderEntity = orderRepository.findById(requestDish.getOrderId())
                    .orElseThrow(() -> new EntityNotFoundException("Order not found"));
            dishEntity.setOrder(orderEntity);
        } else {
            dishEntity.setOrder(null);
        }

        dishEntity = repository.save(dishEntity);

        return ResponseDish.toModel(dishEntity);
    }

    public Long deleteById(Long id) {
        repository.deleteById(id);
        return id;
    }
}
