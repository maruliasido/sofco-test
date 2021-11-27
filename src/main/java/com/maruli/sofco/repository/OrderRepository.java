package com.maruli.sofco.repository;

import com.maruli.sofco.entity.Order;
import com.maruli.sofco.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "SELECT * FROM orders WHERE order_id = :id",nativeQuery = true)
    public Order getOrderByOrderId(@Param("id") String id);

}
