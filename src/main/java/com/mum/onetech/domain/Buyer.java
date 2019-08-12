package com.mum.onetech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "buyer")
    private List<Review> reviews;
}
