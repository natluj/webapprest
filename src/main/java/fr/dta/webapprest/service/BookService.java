package fr.dta.webapprest.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.webapprest.model.Book;

@Service
@Transactional
public class BookService {
	
	@PersistenceContext
	private EntityManager em;

	public static BookService instance = null;
	
	public BookService() {
	}

	public static BookService getInstance() {
		if (instance != null)
			return instance;
		else
			instance = new BookService();
		return instance;
	}

	public boolean contains(int id) {
		return getById(id) != null;
	}
	
	public void create(Book book) {
		em.persist(book);
	}

	public void update(Book book) {
		em.merge(book);
	}

	public void delete(int id) {
		em.remove(getById(id));
	}

	public List<Book> getAll() {
		List<Book> books = new ArrayList<>();
		try {
			TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
			books = query.getResultList();
		} catch(NoResultException e) {
			return books;
		}

		return books;			

	}

	public Book getById(int idBook) {
		TypedQuery<Book> query = em.createQuery("select b from Book b where b.id =:bookId ", Book.class)
									.setParameter("bookId", idBook);

		return query.getSingleResult();
	}

}


