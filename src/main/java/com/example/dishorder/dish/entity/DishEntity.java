package com.example.dishorder.dish.entity;

import com.example.dishorder.order.entity.OrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Entity
    @Data
    @Table(name = "app_dish")
    @NoArgsConstructor
    @AllArgsConstructor
    public class DishEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String description;
        private double price;

        @ManyToOne
        @JoinColumn(name = "order_id")
        private OrderEntity order;


    }
