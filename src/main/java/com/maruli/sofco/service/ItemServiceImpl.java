package com.maruli.sofco.service;

import com.maruli.sofco.entity.Item;
import com.maruli.sofco.entity.Order;
import com.maruli.sofco.entity.OrderItem;
import com.maruli.sofco.entity.User;
import com.maruli.sofco.object.AddItem;
import com.maruli.sofco.object.BuyItem;
import com.maruli.sofco.repository.ItemRepository;
import com.maruli.sofco.repository.OrderItemRepository;
import com.maruli.sofco.repository.OrderRepository;
import com.maruli.sofco.repository.UserRepository;
import com.maruli.sofco.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<Item> itemList() {
        return itemRepository.findAll();
    }

    @Override
    public void createOrUpdateItem(AddItem addItem) {
        Item item = new Item();
        item.setName(addItem.getName());
        item.setDescription(addItem.getDescription());
        item.setStock(addItem.getStock());
        itemRepository.save(item);
    }

    @Override
    public Boolean updateStockItem(Long id, Integer stock) {
        Item item = itemRepository.findById(id).get();
        if (item != null) {
            itemRepository.delete(item);
            return true;
        }
        return false;
    }

    @Override
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id).get();
        itemRepository.delete(item);
    }

    @Override
    public String buyItem(List<BuyItem> buyItem,
                        Principal principal,
                        String note) {

        Order order = new Order();
        order.setOrderDate(Constant.getTimestamp());
        User user = userRepository.getUserByUsername(principal.getName());

        List<Item> itemList = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();

        //mengurangi stock item
        for (int i = 0; i < buyItem.size(); i++) {
            Item item = itemRepository.getItemByItemSku(buyItem.get(i).getItemSku());
            System.out.println("ini stock"+item.getStock());
            item.setStock(item.getStock()-buyItem.get(i).getQuantity());
            itemList.add(item);
            itemRepository.save(item);
        }

        //bikin OrderItem
        for (int i = 0; i<itemList.size(); i++){
            OrderItem oi = new OrderItem();
            oi.setItem(itemList.get(i));
            oi.setOrders(order);
            oi.setItemName(itemList.get(i).getName());
            oi.setPrice(itemList.get(i).getPrice());
            oi.setQuantityBelanja(buyItem.get(i).getQuantity());
            oi.setItemSku(buyItem.get(i).getItemSku());
            orderItems.add(oi);
        }
        String code = generateCode(principal.getName());

        orderItemRepository.saveAll(orderItems);
        order.setOrderNote(note);
        order.setUser(user);
        order.setOrderId(code);

        orderRepository.save(order);

        return code;

    }

    private static String generateCode(String name){
        String code = name+ Constant.getTimestamp()/100000;
        return code;
    }
}
