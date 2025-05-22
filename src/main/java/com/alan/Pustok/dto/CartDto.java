package com.alan.Pustok.dto;

import java.math.BigDecimal;

import com.alan.Pustok.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
  private int quantity;
  private Book book;
  private BigDecimal totalPrice;
}
