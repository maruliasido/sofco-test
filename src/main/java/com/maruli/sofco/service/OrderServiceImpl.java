package com.maruli.sofco.service;

import com.maruli.sofco.entity.Item;
import com.maruli.sofco.entity.Order;
import com.maruli.sofco.entity.OrderItem;
import com.maruli.sofco.entity.User;
import com.maruli.sofco.repository.ItemRepository;
import com.maruli.sofco.repository.OrderItemRepository;
import com.maruli.sofco.repository.OrderRepository;
import com.maruli.sofco.repository.UserRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<Order> showUserOrder(Principal principal) {
        User user = userRepository.getUserByUsername(principal.getName());
        List<Order> orderList = orderRepository.findAll().stream().
                filter(order -> order.getUser().equals(user)).collect(Collectors.toList());
        return orderList;
    }

    @Override
    public String editQuantity( String orderId, Integer itemSku, Integer quantityAfter,Principal principal) {
        Order order = orderRepository.getOrderByOrderId(orderId);
        List<OrderItem> orderItem = orderItemRepository.getOrderItem(order.getId());
        System.out.println("length"+ orderItem.size());
        for (OrderItem oi: orderItem){
            System.out.println(oi.getItem().getItemSku());
        }
        OrderItem toEditQuantity = orderItem.stream().filter(o -> o.getItemSku().equals(itemSku)).findFirst().get();
        Integer quantityBefore = toEditQuantity.getQuantityBelanja();
        Integer difQuantity = quantityBefore - quantityAfter;
        toEditQuantity.setQuantityBelanja(quantityAfter);
        orderItemRepository.save(toEditQuantity);

        Item item = itemRepository.getItemByItemSku(itemSku);
        item.setStock(item.getStock() + difQuantity);
        itemRepository.save(item);

        String response = "Item " + toEditQuantity.getItem().getName() +
                " quantity has changed from " + quantityBefore +" to "+ quantityAfter;

        return response;
    }
}
