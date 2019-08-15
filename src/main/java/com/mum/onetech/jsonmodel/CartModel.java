package com.mum.onetech.jsonmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartModel {
    private Integer itemCount = 0;
    private Double totalAmount = 0.0;
    private GenericJsonRespModel genericRes = new GenericJsonRespModel();

    public CartModel(Integer itemCount, Double totalAmount) {
        this.itemCount = itemCount;
        this.totalAmount = totalAmount;
    }
}
