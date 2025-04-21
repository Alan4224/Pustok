package com.alan.Pustok.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Book extends AbstractEntity {

    @Column(name = "provider_id", nullable = false)
    private int providerId;

    @Column(name = "book_type_id", nullable = false)
    private int bookTypeId;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "price", nullable = true)
    private BigDecimal price;

    @Column(name = "image", nullable = true, length = -1)
    private String image;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "sold", nullable = true)
    private Integer sold;

    @Column(name = "viewed", nullable = true)
    private Integer viewed;

    @Column(name = "introduction", nullable = true, length = -1)
    private String introduction;

    @Column(name = "author", nullable = true, length = 70)
    private String author;

    @Column(name = "translator", nullable = true, length = 35)
    private String translator;

    @Column(name = "publisher", nullable = true, length = 30)
    private String publisher;

    @Column(name = "release_year", nullable = true)
    private Integer releaseYear;

    @Column(name = "weight", nullable = true)
    private Float weight;

    @Column(name = "packaging_size", nullable = true, length = 20)
    private String packagingSize;

    @Column(name = "total_page", nullable = true)
    private Integer totalPage;

    @Column(name = "format", nullable = true, length = 15)
    private String format;

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "book_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference
    private BookType bookType;

}
