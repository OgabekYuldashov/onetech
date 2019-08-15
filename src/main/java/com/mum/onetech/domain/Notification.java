package com.mum.onetech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String message;


    private Boolean isRead = false;

    @ManyToOne(cascade = CascadeType.ALL)
    private Buyer receiver;

    @ManyToOne(cascade = CascadeType.ALL)
    private Seller sender;

    public Notification(@NotBlank String message, Buyer receiver, Seller sender) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
    }
}
