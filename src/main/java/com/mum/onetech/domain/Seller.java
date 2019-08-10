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
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Credentials credentials;

    @OneToOne
    private Address address;

    @OneToMany
    private List<Product> products;

    @ManyToMany(mappedBy = "sellers")
    private List<Buyer> followers;


    @OneToMany(mappedBy = "sender")
    private List<Notification> notificationsSent;
}
