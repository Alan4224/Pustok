package com.alan.Pustok.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alan.Pustok.dto.BookTypeDto;
import com.alan.Pustok.entity.BookType;
import com.alan.Pustok.repository.BookTypeRepository;
import com.alan.Pustok.service.BookTypeService;

@Service
public class BookTypeServiceImpl implements BookTypeService {
    @Autowired
    BookTypeRepository bookTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BookType> findAll() {
        return bookTypeRepository.findAll();
    }

    @Override
    public BookType create(BookTypeDto bookTypeDto) {
        BookType bookType = modelMapper.map(bookTypeDto, BookType.class);
        return bookTypeRepository.save(bookType);
    }

    @Override
    public void delete(int id) {
        bookTypeRepository.deleteById(id);
    }

    @Override
    public BookType findById(int id) {
        return bookTypeRepository.findById(id).orElse(null);
    }

}
