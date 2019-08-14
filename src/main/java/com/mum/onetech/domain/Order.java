package com.mum.onetech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private OrderStatus status = OrderStatus.PENDING;

    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Valid
    @OneToOne
    private Address billingAddr;

    @Valid
    @OneToOne
    private Address shippingAddr;

    @OneToMany
    private List<OrderItem> orderItems;

    @ManyToOne(cascade = CascadeType.ALL)
    private Buyer buyer;

}