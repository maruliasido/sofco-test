package com.maruli.sofco.repository;

import com.maruli.sofco.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
    public Item getItemByItemSku(Integer sku);
}
