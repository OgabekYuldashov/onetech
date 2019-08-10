package com.mum.onetech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
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
}
