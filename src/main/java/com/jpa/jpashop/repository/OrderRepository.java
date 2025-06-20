package com.jpa.jpashop.repository;

import com.jpa.jpashop.entity.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(em.find(Order.class, id));
    }
}
