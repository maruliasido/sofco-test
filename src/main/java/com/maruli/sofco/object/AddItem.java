package com.maruli.sofco.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItem {
    private String name;
    private String description;
    private Integer stock;
}
