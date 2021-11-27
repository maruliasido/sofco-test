package com.maruli.sofco.controller;

import com.maruli.sofco.entity.Order;
import com.maruli.sofco.entity.OrderItem;
import com.maruli.sofco.object.BasicResponse;
import com.maruli.sofco.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;


    @GetMapping("/show-orders")
    public ResponseEntity<?> showUserOrders(@RequestHeader("Authorization") String auth, Principal principal){
        try {
            List<Order> orderList = orderService.showUserOrder(principal);
            return ResponseEntity.ok(orderList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new BasicResponse(false,"Internal Server Error"));
        }
    }

    @GetMapping("/edit-quantity")
    public ResponseEntity<?> showOrderItem(@RequestHeader("Authorization") String auth,
                                           @RequestParam("Order ID") String orderId,
                                           @RequestParam("Item SKU") Integer itemSku,
                                           @RequestParam("Expected Quantity") Integer quantity,
                                           Principal principal){
        try {
            String response = orderService.editQuantity(orderId,itemSku,quantity,principal);
            return ResponseEntity.ok(new BasicResponse(true,response));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new BasicResponse(false,"Internal Server Error"));
        }
    }

}
