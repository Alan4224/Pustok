package com.alan.Pustok.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.alan.Pustok.entity.Book;
import com.alan.Pustok.service.BookService;
import com.alan.Pustok.service.BookTypeService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookTypeService bookTypeService;

    @GetMapping("")
    public String getHomePage(Model model) {
        model.addAttribute("allBookTypes", bookTypeService.findAll());
        model.addAttribute("bestSellers", bookService.findBooks(0, 12, "sold"));
        model.addAttribute("featuredBooks", bookService.findBooks(0, 24, "sold"));
        model.addAttribute("newArrivals", bookService.findBooks(0, 24, "createAt"));
        model.addAttribute("mostViews", bookService.findBooks(0, 24, "viewed"));
        return "client/home-page";
    }

    @GetMapping("/danh-muc")
    public String getBooksByType(@RequestParam int id, @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size, Model model) {
        model.addAttribute("allBookTypes", bookTypeService.findAll());
        model.addAttribute("books", bookService.findByBookTypeId(id, page - 1, size));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookService.findByBookTypeId(id, page - 1, size).getTotalPages());
        model.addAttribute("bookType", bookTypeService.findById(id));
        return "client/shop-grid";
    }

    @GetMapping("/sach")
    public String getBookDetail(@RequestParam int id, Model model) {
        model.addAttribute("allBookTypes", bookTypeService.findAll());
        Book book = bookService.findBookById(id);
        if (book == null) {
            return "client/404";
        }
        model.addAttribute("book", book);
        model.addAttribute("relatedBooks", bookService.findRelatedBooks(book.getAuthor(), book.getId()));
        return "client/product-details";
    }

}
