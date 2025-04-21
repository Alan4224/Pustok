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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alan.Pustok.dto.BookDto;
import com.alan.Pustok.service.BookService;
import com.alan.Pustok.service.BookTypeService;
import com.alan.Pustok.service.ProviderService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private BookTypeService bookTypeService;

    @GetMapping("")
    public String getBook(Model model) {
        model.addAttribute("allBooks", bookService.findAll());
        return "admin/book/show";
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        model.addAttribute("newBook", new BookDto());
        model.addAttribute("allBookTypes", bookTypeService.findAll());
        model.addAttribute("allProviders", providerService.findAll());
        return "admin/book/create";
    }

    @PostMapping("/create")
    public String createBookPost(@ModelAttribute("newBook") @Valid BookDto bookDto,
            BindingResult result, @RequestParam("file") MultipartFile file, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allBookTypes", bookTypeService.findAll());
            model.addAttribute("allProviders", providerService.findAll());
            return "admin/book/create";
        }
        bookService.create(bookDto, file);
        return "redirect:/admin/book";
    }

    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable int id, Model model) {
        model.addAttribute("currentBook", bookService.findBookById(id));
        return "admin/book/detail";
    }

    @GetMapping("/update/{id}")
    public String updateBook(@PathVariable int id, Model model) {
        model.addAttribute("currentBook", bookService.findBookById(id));
        model.addAttribute("newBook", new BookDto());
        model.addAttribute("allBookTypes", bookTypeService.findAll());
        model.addAttribute("allProviders", providerService.findAll());
        return "admin/book/update";
    }

    @PostMapping("/update/{id}")
    public String updateBookPost(@PathVariable int id,
            @ModelAttribute("newBook") @Valid BookDto bookDto, BindingResult result,
            @RequestParam("file") MultipartFile file, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("currentBook", bookService.findBookById(id));
            model.addAttribute("allBookTypes", bookTypeService.findAll());
            model.addAttribute("allProviders", providerService.findAll());
            return "admin/book/update";
        }
        bookService.update(id, bookDto, file);
        return "redirect:/admin/book";
    }

    @GetMapping("/delete/{id}")
    public String getMethodName(@PathVariable int id) {
        bookService.delete(id);
        return "redirect:/admin/book";
    }

}
