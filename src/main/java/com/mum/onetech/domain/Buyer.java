package com.mum.onetech.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class Buyer {

    public Buyer() {
//        this.shoppingCart = new Cart();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL)
    @Valid
    private Credentials credentials;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Address> shippingAddresses;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Address> billingAddresses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Seller> sellers;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "receiver")
    private List<Notification> notificationsReceived;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "byr")
    private List<Review> reviews;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Product> favoriteProducts = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "buyer")
    private List<BuyerOrder> buyerOrders = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Cart shoppingCart = new Cart(this);

    public void addOrder(BuyerOrder buyerOrder){
        buyerOrders.add(buyerOrder);
    }

}
