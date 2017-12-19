package fr.dta.webapprest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.webapprest.model.Book;
import fr.dta.webapprest.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;
	
	//Sémantique CRUD
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public Book getBook(@PathVariable("id") int idBook) {
		Book book = bookService.getById(idBook);
		return book;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> getAll() {
		return bookService.getAll();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Book createBook(@RequestBody @Valid Book book/*, BindingResult br*/) {
		/*if (br.hasErrors()) {
			System.out.println(br.getErrorCount());
		}*/
		bookService.create(book);
		return book;
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Book updateBook(@RequestBody Book book, @PathVariable("id") int idBook) {
		Book b = bookService.getById(idBook);
		b.setTitle(book.getTitle());
		b.setNbPages(book.getNbPages());
		
		b.setIsbn(book.getIsbn());
		
//		b.setKeywords(book.getKeywords());
		return b;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public int deleteBook(@PathVariable int id) {
		bookService.delete(id);
		return id;
	}
}
