package com.library.librayapi.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.library.librayapi.exception.BusinessException;
import com.library.librayapi.model.entity.Book;
import com.library.librayapi.model.repository.BookRepository;
import com.library.librayapi.service.impl.BookServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

	BookService service;

	@MockBean
	BookRepository bookRespository;

	@BeforeEach
	private void setUp() {
		this.service = new BookServiceImpl(bookRespository);

	}

	@Test
	@DisplayName("Deve salvar um livro")
	public void saveBookTest() {
		// cenario
		Book book = createValidBook();
		Mockito.when(bookRespository.existsByIsbn(Mockito.anyString())).thenReturn(false);
		Mockito.when(bookRespository.save(book))
				.thenReturn(Book.builder().id("01").title("as aventuras").author("autor").isbn("123").build());

		// execucao
		Book savedBook = service.save(book);

		// verificacao
		assertThat(savedBook.getId()).isNotNull();
		assertThat(savedBook.getTitle()).isEqualTo("as aventuras");
		assertThat(savedBook.getAuthor()).isEqualTo("autor");
		assertThat(savedBook.getIsbn()).isEqualTo("123");

	}

	@Test
	@DisplayName("Deve lançar erro de negocio ao tentar salvar um livro com isbn duplicado")
	public void shouldNotSaveABookWithDuplicatedISBN() {
		// cenario
		Book book = createValidBook();
		
		Mockito.when(bookRespository.existsByIsbn(Mockito.anyString())).thenReturn(true);

		// execucao
		Throwable throwable = Assertions.catchThrowable(() -> service.save(book));

		// verificacao
		assertThat(throwable)
					.isInstanceOf(BusinessException.class)
					.hasMessage("ISBN já cadastrado");		
		
		Mockito.verify(bookRespository, Mockito.never()).save(book);
	}

	private Book createValidBook() {
		return Book.builder().title("as aventuras").author("autor").isbn("123").build();
	}

}
