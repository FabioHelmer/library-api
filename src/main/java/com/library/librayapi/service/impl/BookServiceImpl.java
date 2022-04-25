package com.library.librayapi.service.impl;

import org.springframework.stereotype.Service;

import com.library.librayapi.exception.BusinessException;
import com.library.librayapi.model.entity.Book;
import com.library.librayapi.model.repository.BookRepository;
import com.library.librayapi.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	
	
	private BookRepository repository;
	
	public BookServiceImpl(BookRepository repository) {
		this.repository = repository;
	}
	
	
	@Override
	public Book save(Book book) {
		if(repository.existsByIsbn(book.getIsbn())) {
			throw new BusinessException("ISBN já cadastrado");
		}
		return repository.save(book);
	}

}
