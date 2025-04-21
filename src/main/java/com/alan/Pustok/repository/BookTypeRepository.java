package com.alan.Pustok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alan.Pustok.entity.BookType;

@Repository
public interface BookTypeRepository extends JpaRepository<BookType, Integer> {

}
