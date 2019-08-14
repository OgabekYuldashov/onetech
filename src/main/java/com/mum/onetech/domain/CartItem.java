package com.mum.onetech.domain;

import com.mum.onetech.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double unitPrice;


    @OneToOne
    private Product product;

    public CartItem(Product product, Integer quantity) {
        this.quantity = quantity;
        this.product = product;
        this.unitPrice = product.getPrice();
    }

    public Double getTotal(){
        return Double.valueOf(Util.df2.format(unitPrice * quantity));
    }
}
