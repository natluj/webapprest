package fr.dta.webapprest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.webapprest.model.Book;
import fr.dta.webapprest.service.LibraryService;

@RestController("/books")
public class LibraryController {
	
	//Sémantique CRUD

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Book getBook(@PathVariable("id") int idBook) {
		Book book = LibraryService.getById(idBook);
		return book;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> getAll() {
		return LibraryService.getAll();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Book createBook(@RequestBody @Valid Book book/*, BindingResult br*/) {
		/*if (br.hasErrors()) {
			System.out.println(br.getErrorCount());
		}*/
		LibraryService.addBook(book);
		return book;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Book updateBook(@RequestBody Book book, @PathVariable("id") int idBook) {
		Book b = LibraryService.getById(idBook);
		b.setTitle(book.getTitle());
		b.setNbPages(book.getNbPages());
		
		b.setIsbn(book.getIsbn());
		
		b.setKeywords(book.getKeywords());
		return b;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public int deleteBook(@PathVariable int id) {
		LibraryService.removeBook(id);
		return id;
	}
}
