package com.mum.onetech.jsonmodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartModel {
    private Integer itemCount = 0;
    private Double totalAmount = 0.0;
}
