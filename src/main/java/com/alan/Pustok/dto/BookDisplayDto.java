package com.alan.Pustok.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BookDisplayDto {
    private int id;
    private String name;
    private String image;
    private BigDecimal price;
    private String author;
}
