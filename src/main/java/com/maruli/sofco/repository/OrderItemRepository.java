package com.maruli.sofco.repository;


import com.maruli.sofco.entity.Order;
import com.maruli.sofco.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    @Query(value = "SELECT * FROM order_item WHERE order_id = :id",nativeQuery = true)
    public List<OrderItem> getOrderItem(@Param("id") Long id);


}
