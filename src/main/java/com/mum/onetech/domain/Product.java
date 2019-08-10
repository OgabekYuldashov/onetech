package com.mum.onetech.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @ManyToOne
    private Category category;

    @NotNull
    private Double price;

    @ManyToOne
    private Seller seller;

    @OneToMany
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

}
