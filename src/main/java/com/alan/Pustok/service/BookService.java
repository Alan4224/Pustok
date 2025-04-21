package com.alan.Pustok.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alan.Pustok.dto.BookDto;
import com.alan.Pustok.entity.Book;

@Service
public interface BookService {
    List<Book> findAll();

    Book create(BookDto bookdDto, MultipartFile file);

    Book update(int id, BookDto bookDto, MultipartFile file);

    Book findBookById(int id);

    void delete(int id);
}
