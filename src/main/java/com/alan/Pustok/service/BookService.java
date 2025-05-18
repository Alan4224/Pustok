package com.alan.Pustok.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alan.Pustok.dto.BookDisplayDto;
import com.alan.Pustok.dto.BookDto;
import com.alan.Pustok.entity.Book;

@Service
public interface BookService {
    List<Book> findAll();

    Book create(BookDto bookdDto, MultipartFile file);

    Book update(int id, BookDto bookDto, MultipartFile file);

    Book findBookById(int id);

    void delete(int id);

    List<BookDisplayDto> findBooks(int page, int size, String sortBy);

    Page<BookDisplayDto> findByBookTypeId(int bookTypeId, int page, int size);

    List<BookDisplayDto> findRelatedBooks(String author, int id);
}
