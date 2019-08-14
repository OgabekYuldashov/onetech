package com.mum.onetech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import javax.validation.Valid;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Credentials credentials;

    @OneToMany
    private List<Address> shippingAddresses;

    @OneToMany
    private List<Address> billingAddresses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Seller> sellers;

    @OneToMany(mappedBy = "receiver")
    private List<Notification> notificationsReceived;


    @OneToMany(mappedBy = "buyer")
    private List<Review> reviews;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "buyer")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL)
    private List<Product> favoriteProducts = new ArrayList<>();



    @OneToOne(cascade = CascadeType.ALL)
    private Cart shoppingCart;

}
