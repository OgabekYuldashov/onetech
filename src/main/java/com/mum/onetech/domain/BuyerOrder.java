package com.mum.onetech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BuyerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;


    @Temporal(TemporalType.DATE)
    private Date createDate;


    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Address billingAddr;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Address shippingAddr;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Buyer buyer;

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
    }
    public BuyerOrder(Buyer buyer) {
        this.buyer = buyer;
    }
}
