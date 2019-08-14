package com.mum.onetech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double unitPrice;



    @OneToOne(cascade =CascadeType.ALL)
    private Product product;

    public OrderItem(Product product, Integer quantity) {
        this.quantity = quantity;
        this.product = product;
        this.unitPrice = product.getPrice();
    }

    @ManyToOne
//   @JoinTable
    private Order order;

}
