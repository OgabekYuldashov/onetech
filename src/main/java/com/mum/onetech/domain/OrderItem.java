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

    @Enumerated(EnumType.STRING)
    private OrderItemStatus orderItemStatus = OrderItemStatus.PENDING;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;

    public OrderItem(Product product, Integer quantity) {
        this.quantity = quantity;
        this.product = product;
        this.unitPrice = product.getPrice();
    }

    @ManyToOne(cascade = CascadeType.ALL)
//   @JoinTable
    private BuyerOrder buyerOrder;

    public OrderItem(Integer quantity, Double unitPrice, Product product, BuyerOrder buyerOrder) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.product = product;
        this.buyerOrder = buyerOrder;
    }
}
