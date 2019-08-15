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
    private List<Address> shippingAddresses = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL)
    private List<Address> billingAddresses = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Seller> sellers = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "receiver")
    private List<Notification> notificationsReceived = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "byr")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL)
    private List<Product> favoriteProducts = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "buyer")
    private List<BuyerOrder> buyerOrders = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID")
    private Cart shoppingCart = new Cart(this);

    public void addOrder(BuyerOrder buyerOrder){
        buyerOrders.add(buyerOrder);
    }
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "buyer")
    private List<BuyerOrder> orders = new ArrayList<>();


    public void addReview(Review review){
        reviews.add(review);
    }

    public void addNotification(Notification notification){
        notificationsReceived.add(notification);
    }

    public void addBillingAddress(Address address){
        billingAddresses.add(address);
    }

    public void addShippingAddress(Address address){
        shippingAddresses.add(address);
    }

    public void addSellerToFollow(Seller seller){
        if (sellers.stream().noneMatch(s -> s.getId().equals(seller.getId()))) {
            sellers.add(seller);
        }
    }
}
