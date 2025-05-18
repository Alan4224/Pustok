package com.alan.Pustok.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alan.Pustok.dto.BookDisplayDto;
import com.alan.Pustok.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select new com.alan.Pustok.dto.BookDisplayDto(b.id, b.name, b.image, b.price, b.author) from Book b")
    List<BookDisplayDto> findBooks(Pageable pageable);

    @Query("select new com.alan.Pustok.dto.BookDisplayDto(b.id, b.name, b.image, b.price, b.author) from Book b where b.bookType.id = ?1")
    Page<BookDisplayDto> findByBookTypeId(int bookTypeId, Pageable pageable);

    @Query("select new com.alan.Pustok.dto.BookDisplayDto(b.id, b.name, b.image, b.price, b.author) from Book b where b.author = ?1 and b.id <> ?2")
    List<BookDisplayDto> findByAuthorExcluding(String author, int id, Pageable pageable);
}
