package com.maruli.sofco.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "quantity_belanja")
    private Integer quantityBelanja;

    @Column(name = "price")
    private Integer price;

    @Column(name = "item_sku")
    private Integer itemSku;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private Order orders;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "item_id")
    private Item item;


}
