package com.library.librayapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.librayapi.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

	boolean existsByIsbn(String isbn);

}
