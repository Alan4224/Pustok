package com.alan.Pustok.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alan.Pustok.dto.BookTypeDto;
import com.alan.Pustok.service.BookTypeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/book-type")
public class BookTypeController {
    @Autowired
    private BookTypeService bookTypeService;

    @GetMapping("")
    public String getBookType(Model model) {
        model.addAttribute("allTypes", bookTypeService.findAll());
        model.addAttribute("newType", new BookTypeDto());
        return "admin/book-type/show";
    }

    @PostMapping("/create")
    public String createTypePost(Model model, @ModelAttribute("newType") @Valid BookTypeDto bookTypeDto,
            BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("allTypes", bookTypeService.findAll());
            return "admin/book-type/show";
        }
        bookTypeService.create(bookTypeDto);
        return "redirect:/admin/book-type";
    }

    @GetMapping("/delete/{id}")
    public String getMethodName(@PathVariable int id) {
        bookTypeService.delete(id);
        return "redirect:/admin/book-type";
    }

}
