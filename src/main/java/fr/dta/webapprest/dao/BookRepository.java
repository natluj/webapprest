package fr.dta.webapprest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.webapprest.model.Book;

@Repository
@Transactional
public class BookRepository {

	public BookRepository() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	public EntityManager em;

	protected Session getSession() {
		return em.unwrap(Session.class);
	}

	public void persist(Object entity) {
		em.persist(entity);
	}

	public List<Book> getAll() { //ok
		TypedQuery<Book> allBooks = em.createQuery("SELECT b FROM Book b", Book.class);
		return allBooks.getResultList();
	}

	public Book getById(int id) { //ok
		TypedQuery<Book> book = em.createQuery("SELECT b FROM Book b WHERE id=" + id, Book.class);
		return book.getSingleResult();
	}

	public void add(Book book) { //ok
		try {
			em.persist(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("A book has been added !");
	}

	public void delete(Book b) {
		Book book = getById(b.getId());
		em.remove(book);
	}

	public void update(Book b) {
		Book book = getById(b.getId());
		em.merge(book);
		System.out.println("Le livre n°" + b.getId() + " has been updated !");
	}

}
