package com.mum.onetech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date datePosted;

    @NotBlank
    private String title;

    @NotBlank
    private String message;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status = ReviewStatus.PENDING;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    private Buyer byr;

    public Review(@NotBlank String title, @NotBlank String message, Date datePosted, Product product, Buyer byr) {
        this.datePosted = datePosted;
        this.title = title;
        this.message = message;
        this.product = product;
        this.byr = byr;
    }
}
