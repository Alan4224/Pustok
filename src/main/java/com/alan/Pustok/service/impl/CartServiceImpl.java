package com.alan.Pustok.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.Pustok.dto.CartDto;
import com.alan.Pustok.entity.Book;
import com.alan.Pustok.service.BookService;
import com.alan.Pustok.service.CartService;

@Service
public class CartServiceImpl implements CartService {
  @Autowired
  private BookService bookService;

  @Override
  public HashMap<Integer, CartDto> addCart(int id, int quantity, HashMap<Integer, CartDto> cart) {
    CartDto cartDto = new CartDto();
    Book book = bookService.findBookById(id);
    if (book != null && cart.containsKey(id)) {
      cartDto = cart.get(id);
      cartDto.setQuantity(cartDto.getQuantity() + quantity);
      cartDto.setTotalPrice(book.getPrice().multiply(BigDecimal.valueOf(cartDto.getQuantity())));
    } else {
      cartDto.setBook(book);
      cartDto.setQuantity(quantity);
      cartDto.setTotalPrice(book.getPrice().multiply(BigDecimal.valueOf(cartDto.getQuantity())));
    }
    cart.put(id, cartDto);
    return cart;
  }

  @Override
  public HashMap<Integer, CartDto> editCart(int id, int quantity, HashMap<Integer, CartDto> cart) {
    if (cart == null) {
      return cart;
    }
    CartDto cartDto = new CartDto();
    if (cart.containsKey(id)) {
      cartDto = cart.get(id);
      cartDto.setQuantity(quantity);
      cartDto.setTotalPrice(cartDto.getBook().getPrice().multiply(BigDecimal.valueOf(cartDto.getQuantity())));
    }
    cart.put(id, cartDto);
    return cart;
  }

  @Override
  public HashMap<Integer, CartDto> deleteCart(int id, HashMap<Integer, CartDto> cart) {
    if (cart == null) {
      return cart;
    }
    if (cart.containsKey(id)) {
      cart.remove(id);
    }
    return cart;
  }

  @Override
  public int totalQuantity(HashMap<Integer, CartDto> cart) {
    int total = 0;
    for (Map.Entry<Integer, CartDto> item : cart.entrySet()) {
      total += item.getValue().getQuantity();
    }
    return total;
  }

  @Override
  public BigDecimal totalPrice(HashMap<Integer, CartDto> cart) {
    BigDecimal total = BigDecimal.ZERO; // Proper initialization
    for (Map.Entry<Integer, CartDto> item : cart.entrySet()) {
      total = total.add(item.getValue().getTotalPrice()); // Use .add() instead of +=
    }
    return total;
  }

}
