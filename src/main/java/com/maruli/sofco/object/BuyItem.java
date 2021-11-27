package com.maruli.sofco.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyItem {
    private Integer itemSku;
    private Integer quantity;
}
