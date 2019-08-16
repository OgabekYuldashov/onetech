package com.mum.onetech.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mum.onetech.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Brand brand;
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Digits(integer = 8, fraction = 2, message = "{Digits.Product.price.validation}")
    @NotNull
    private Double price;

    //Don't set it in the from
    private Double oldPrice;
    @Temporal(TemporalType.DATE)
    private Date dateProductAdded;

    @Digits(integer = 8, fraction = 2, message = "{Digits.Product.price.validation}")
   private Double discountRate=0.0 ;


    private Boolean isNewArrival = false;

    @Enumerated(EnumType.STRING)
    private PromoteType promote=PromoteType.NONE;
    @ManyToOne
    private Seller seller;

    @OneToMany(cascade = CascadeType.ALL)
    @Valid
    @Fetch(FetchMode.JOIN)
    private List<ProductImage> productImgs;

    @Transient
    private MultipartFile[] productImages  ;
     @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Review> reviews;

//    private String pictureUrls;

    public void calculateDiscount(Double discRate){
        this.discountRate = discRate;
        this.oldPrice = this.price;
        this.price -= this.price *discRate / 100;
        this.price = Double.valueOf(Util.df2.format(this.price));
    }

    public List<Review> getPublicReviews(){
        return reviews.stream().filter(review -> review.getStatus().equals(ReviewStatus.APPROVED)).collect(Collectors.toList());
    }
}
