package com.mum.onetech.jsonmodel;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartItemModel {
    @NotNull
    private Long pid;
    @NotNull
    private Integer quantity;
}
