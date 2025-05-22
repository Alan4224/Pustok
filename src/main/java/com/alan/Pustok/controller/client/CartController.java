package com.alan.Pustok.controller.client;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.alan.Pustok.dto.CartDto;
import com.alan.Pustok.service.BookService;
import com.alan.Pustok.service.BookTypeService;
import com.alan.Pustok.service.CartService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
  @Autowired
  private CartService cartService;

  @Autowired
  private BookTypeService bookTypeService;

  @Autowired
  private BookService bookService;

  @RequestMapping("/add-cart")
  public String addCart(HttpServletRequest request, HttpSession session, @RequestParam int id,
      @RequestParam(defaultValue = "1") int quantity) {
    HashMap<Integer, CartDto> cart = (HashMap<Integer, CartDto>) session.getAttribute("cart");
    if (cart == null) {
      cart = new HashMap<Integer, CartDto>();
    }
    cart = cartService.addCart(id, quantity, cart);
    session.setAttribute("cart", cart);
    session.setAttribute("totalPrice", cartService.totalPrice(cart));
    session.setAttribute("totalQuantity", cartService.totalQuantity(cart));
    return "redirect:" + request.getHeader("Referer");
  }

  @GetMapping("/delete-cart/{id}")
  public String deleteCart(HttpServletRequest request, HttpSession session, @PathVariable int id) {
    HashMap<Integer, CartDto> cart = (HashMap<Integer, CartDto>) session.getAttribute("cart");
    cart = cartService.deleteCart(id, cart);
    session.setAttribute("cart", cart);
    session.setAttribute("totalPrice", cartService.totalPrice(cart));
    session.setAttribute("totalQuantity", cartService.totalQuantity(cart));
    return "redirect:" + request.getHeader("Referer");
  }

  @GetMapping("/gio-hang")
  public String getCart(Model model) {
    model.addAttribute("allBookTypes", bookTypeService.findAll());
    model.addAttribute("bestSellers", bookService.findBooks(0, 5, "sold"));
    return "client/cart";
  }

  @PostMapping("/update-card")
  public String updateCard(HttpServletRequest request, HttpSession session, @RequestParam("id") List<Integer> ids,
      @RequestParam("quantity") List<Integer> quantities) {
    HashMap<Integer, CartDto> cart = (HashMap<Integer, CartDto>) session.getAttribute("cart");
    for (int i = 0; i < ids.size(); i++) {
      cart = cartService.editCart(ids.get(i), quantities.get(i), cart);
    }
    session.setAttribute("cart", cart);
    session.setAttribute("totalPrice", cartService.totalPrice(cart));
    session.setAttribute("totalQuantity", cartService.totalQuantity(cart));
    return "redirect:" + request.getHeader("Referer");
  }

}
