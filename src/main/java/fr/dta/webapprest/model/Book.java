package fr.dta.webapprest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import fr.dta.webapprest.validator.ISBN;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq", initialValue = 2, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
	private int id;

	@Column(name = "title")
	@NotBlank
	private String title;

	@Column(name="author")
	@NotBlank
	String author;
	
	@Column(name = "nbpages")
	private Integer nbPages;

	@Column(name = "publicationdate")
	@Temporal(TemporalType.DATE)
	private Date publicationDate;

	@ISBN
	@Column(name = "isbn")
	private String isbn;

//	@Column
//	Set<String> keywords = new HashSet<String>();

	public Book(Integer id, String title, Integer nbPages, String isbn, Date publicationDate) {
		super();
		this.id = id;
		this.title = title;
		this.nbPages = nbPages;
		this.isbn = isbn;
		this.publicationDate = publicationDate;
	}

	public Book() {
		super();
	}

	/*public void add(String... kws) {
		for (String keyword : kws)
			keywords.add(keyword);
	}

	public boolean matches(String s) {
		for (String kw : keywords)
			if (kw.toLowerCase().indexOf(s.trim()) >= 0)
				return true;
		return false;
	}*/

	@Override
	public String toString() {
		return "Book : [id=" + id + ", title=" + title + ", nbPages=" + nbPages + ", Isbn=" + isbn + ", keywords="
				/*+ keywords*/ + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String iSBN) {
		this.isbn = iSBN;
	}

	/*public Set<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}*/

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
}
