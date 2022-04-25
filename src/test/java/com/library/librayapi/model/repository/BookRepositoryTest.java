package com.library.librayapi.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.library.librayapi.model.entity.Book;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	TestEntityManager entityManagerTest;
	
	@Autowired
	BookRepository bookRepository;
	
	@Test
	@DisplayName("deve retornar verdadeiro quando existir um livro na base com o isbn ja cadastrado")
	public void returnTrueWhenIsbnExists() {
		//cenario
		String isbn = "123";
		Book book = Book.builder().title("as aventuras").author("autor").isbn(isbn).build();
		book.newId();
		entityManagerTest.persist(book);
		
		//execucao
		boolean existsByIsbn = bookRepository.existsByIsbn(isbn);
		
		//verificacao
		assertThat(existsByIsbn).isTrue();
	}
	
	@Test
	@DisplayName("deve retornar false quando não existir um livro na base com o isbn ja cadastrado")
	public void returnFalseWhenIsbnNotExists() {
		//cenario
		String isbn = "123";
		
		//execucao
		boolean existsByIsbn = bookRepository.existsByIsbn(isbn);
		
		//verificacao
		assertThat(existsByIsbn).isFalse();
	}

}
