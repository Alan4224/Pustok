package com.alan.Pustok.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alan.Pustok.dto.BookDisplayDto;
import com.alan.Pustok.dto.BookDto;
import com.alan.Pustok.entity.Book;
import com.alan.Pustok.repository.BookRepository;
import com.alan.Pustok.service.BookService;
import com.alan.Pustok.utils.UploadUtil;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(BookDto bookDto, MultipartFile file) {
        bookDto.setImage(UploadUtil.getImageLink(file, Book.class));
        Book book = modelMapper.map(bookDto, Book.class);
        return bookRepository.save(book);
    }

    @Override
    public Book findBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book update(int id, BookDto bookDto, MultipartFile file) {
        Book book = bookRepository.findById(id).get();
        bookDto.setImage(UploadUtil.getImageLink(file, Book.class));
        book = modelMapper.map(bookDto, Book.class);
        return bookRepository.save(book);
    }

    @Override
    public void delete(int id) {
        UploadUtil.deleteImageLink(bookRepository.findById(id).get().getImage(), Book.class);
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDisplayDto> findBooks(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        return bookRepository.findBooks(pageable);
    }

    @Override
    public Page<BookDisplayDto> findByBookTypeId(int bookTypeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findByBookTypeId(bookTypeId, pageable);
    }

    @Override
    public List<BookDisplayDto> findRelatedBooks(String author, int id) {
        Pageable pageable = PageRequest.of(0, 4);
        return bookRepository.findByAuthorExcluding(author, id, pageable);
    }
}
