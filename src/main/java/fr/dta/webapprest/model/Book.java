package fr.dta.webapprest.model;

import java.util.HashSet;
import java.util.Set;

import fr.dta.webapprest.validator.ISBN;


public class Book {
	private Integer id;
	private String title;
	private Integer nbPages;
	
	@ISBN
	private String isbn;
	
	Set<String> keywords=new HashSet<String>();
	
	
	public Book(Integer id, String title, Integer nbPages, String isbn) {
		super();
		this.id = id;
		this.title = title;
		this.nbPages = nbPages;
		this.isbn = isbn;
	}
	
	public Book() {
		super();
	}
	
	public void add(String... kws){
		for(String keyword:kws)keywords.add(keyword);
	}
	
	public boolean matches(String s){
		for(String kw: keywords)if(kw.toLowerCase().indexOf(s.trim())>=0) return true;
		return false;
	}
	
	
	@Override
	public String toString() {
		return "Book : [id=" + id + ", title=" + title + ", nbPages=" + nbPages + ", Isbn=" + isbn+ ", keywords=" + keywords + "]";
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
	
	public Set<String> getKeywords() {
		return keywords;
	}
	
	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}
	
	

}
