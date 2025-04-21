package com.alan.Pustok.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alan.Pustok.dto.BookTypeDto;
import com.alan.Pustok.entity.BookType;

@Service
public interface BookTypeService {
    List<BookType> findAll();

    BookType create(BookTypeDto bookTypeDto);

    void delete(int id);
}
