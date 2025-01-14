package com.example.Orders.Repo;

import com.example.Orders.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {
}
