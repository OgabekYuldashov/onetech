package com.mum.onetech.domain;


import com.mum.onetech.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @NotNull
    private Double price;

    //Don't set it in the from
    private Double oldPrice;
    @Temporal(TemporalType.DATE)
    private Date dateProductAdded;

   private Double discountRate=0.0 ;


    private Boolean isNewArrival = false;
    @Enumerated(EnumType.STRING)
    private PromoteType promote=PromoteType.NONE;
    @ManyToOne
    private Seller seller;
    @OneToMany(cascade = CascadeType.PERSIST)

    private List<ProductImage> productImgs;

     @Transient
    private MultipartFile[] productImages  ;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

//    private String pictureUrls;

    public void calculateDiscount(Double discRate){
        this.discountRate = discRate;
        this.oldPrice = this.price;
        this.price -= this.price *discRate / 100;
        this.price = Double.valueOf(Util.df2.format(this.price));
    }

}
