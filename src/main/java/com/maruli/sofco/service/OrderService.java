package com.maruli.sofco.service;

import com.maruli.sofco.entity.Order;
import com.maruli.sofco.entity.OrderItem;

import java.security.Principal;
import java.util.List;

public interface OrderService {
    public List<Order> showUserOrder(Principal principal);
    public String editQuantity (String orderId,Integer itemSku, Integer quantity,Principal principal);
}
