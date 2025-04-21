package com.alan.Pustok.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alan.Pustok.dto.BookDto;
import com.alan.Pustok.entity.Book;
import com.alan.Pustok.entity.Provider;
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
        return bookRepository.findById(id).get();
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
}
