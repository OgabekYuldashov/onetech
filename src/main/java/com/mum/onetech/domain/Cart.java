package com.mum.onetech.domain;

import com.mum.onetech.util.Util;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@NoArgsConstructor
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy ="shoppingCart" ,cascade = CascadeType.ALL)
    private Buyer buyer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    private Double totalAmount = 0.0;

    public Cart(Buyer buyer) {
        this.buyer = buyer;
    }

    //Add product to the cart OR increase the quantity if already exists
    public void addIncreaseProduct(Product product, Integer quantity){
        boolean productExists = false;
        for(CartItem item : cartItems){
            if(item.getProduct() == product){
                productExists = true;
                item.setQuantity(item.getQuantity() + quantity);
                break;
            }
        }
        if(!productExists) cartItems.add(new CartItem(product, quantity));
        updateTotalAmount();
    }

    public void mergeWithCart(Cart cart){
        for(CartItem item: cart.getCartItems()){
            boolean itemFound = false;
            for(CartItem existingItem: cartItems){
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
        updateTotalAmount();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
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

    private void updateTotalAmount(){
        totalAmount = cartItems.stream().map(item -> item.getProduct().getPrice() * item.getQuantity()).reduce((aDouble, aDouble2) -> aDouble+aDouble2).orElse(0.0);
        totalAmount = Double.valueOf(Util.df2.format(totalAmount));
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void emptyCart(){
        cartItems = new ArrayList<>();
        totalAmount = 0.0;
    }

    public boolean containsOrderItem(Long itemId){
        return cartItems.stream().anyMatch(cartItem -> cartItem.getId().equals(itemId));
    }

    public void removeCartItemById(Long itemId){
        Iterator itr = cartItems.iterator();
        while (itr.hasNext())
        {
            CartItem x = (CartItem)itr.next();
            if (x.getId().equals(itemId)){
                itr.remove();
                break;
            }
        }
        updateTotalAmount();
    }
}
