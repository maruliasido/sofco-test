package com.maruli.sofco.service;

import com.maruli.sofco.entity.Item;
import com.maruli.sofco.entity.OrderItem;
import com.maruli.sofco.object.AddItem;
import com.maruli.sofco.object.BuyItem;

import java.security.Principal;
import java.util.List;

public interface ItemService {

    public List<Item> itemList();
    public void createOrUpdateItem(AddItem addItem);
    public Boolean updateStockItem(Long id, Integer stock);
    public void deleteItem(Long id);
    public String buyItem(List<BuyItem> itemList, Principal principal, String note);

}
