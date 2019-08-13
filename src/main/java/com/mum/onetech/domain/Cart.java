package com.mum.onetech.domain;

import com.mum.onetech.util.Util;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "shoppingCart")
    private Buyer buyer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> cartItems;

    private Double totalAmount;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    //Add product to the cart OR increase the quantity if already exists
    public void addIncreaseProduct(Product product, Integer quantity){
        boolean productExists = false;
        for(OrderItem item : cartItems){
            if(item.getProduct() == product){
                productExists = true;
                item.setQuantity(item.getQuantity() + quantity);
                break;
            }
        }
        if(!productExists) cartItems.add(new OrderItem(product, quantity));
    }

    public void mergeWithCart(Cart cart){
        for(OrderItem item: cart.getCartItems()){
            boolean itemFound = false;
            for(OrderItem existingItem: cartItems){
                if(existingItem.getProduct() == item.getProduct()){
                    int diff = Math.abs(existingItem.getQuantity() - item.getQuantity());
                    if(existingItem.getQuantity() > item.getQuantity()){
                        existingItem.setQuantity(existingItem.getQuantity() - diff);
                    }else {
                        existingItem.setQuantity(existingItem.getQuantity() + diff);
                    }
                    itemFound = true;
                    break;
                }
            }
            if(!itemFound){
                addIncreaseProduct(item.getProduct(), item.getQuantity());
            }
        }
    }

    public List<OrderItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<OrderItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        if(this.buyer == null){
            this.buyer = buyer;
        }
    }

    public double getTotalAmount() {
        totalAmount = cartItems.stream().map(item -> item.getProduct().getPrice() * item.getQuantity()).reduce((aDouble, aDouble2) -> aDouble+aDouble2).orElse(0.0);
        totalAmount = Double.valueOf(Util.df2.format(totalAmount));
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
