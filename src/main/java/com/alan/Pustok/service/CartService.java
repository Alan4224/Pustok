package com.alan.Pustok.service;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.alan.Pustok.dto.CartDto;

@Service
public interface CartService {
  HashMap<Integer, CartDto> addCart(int id, int quantity, HashMap<Integer, CartDto> cart);

  HashMap<Integer, CartDto> editCart(int id, int quantity, HashMap<Integer, CartDto> cart);

  HashMap<Integer, CartDto> deleteCart(int id, HashMap<Integer, CartDto> cart);

  int totalQuantity(HashMap<Integer, CartDto> cart);

  BigDecimal totalPrice(HashMap<Integer, CartDto> cart);
}
