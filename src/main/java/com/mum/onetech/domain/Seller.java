package com.mum.onetech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL)
    private Credentials credentials;

    @OneToOne(cascade=CascadeType.ALL)
    private Address address;

//    @OneToMany(mappedBy = "seller",cascade=CascadeType.ALL)
//    private List<Product> products;

    @ManyToMany(mappedBy = "sellers",cascade=CascadeType.ALL)
    private List<Buyer> followers;


    @OneToMany(mappedBy = "sender",cascade=CascadeType.ALL)
    private List<Notification> notificationsSent;

    public String getFullName(){
        return credentials.getFirstName() + " " + credentials.getLastName();
    }
}
