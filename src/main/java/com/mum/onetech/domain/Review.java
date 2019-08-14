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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String message;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private ReviewStatus status = ReviewStatus.PENDING;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Buyer byr;
}
