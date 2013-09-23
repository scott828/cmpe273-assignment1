package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.BookStatus;
import edu.sjsu.cmpe.library.domain.Review;

/**
 * @author scott
 * 
 */
public class BookResponseDto {

	private long isbn;

	private String title;

	@JsonProperty("publication-date")
	private String publication_date;

	private String language;

	@JsonProperty("num-pages")
	private int num_pages;

	private BookStatus status = BookStatus.available;

	private List<Review> reviews = new ArrayList<Review>();

	// This is the list of author URI
	private List<LinkDto> authors = new ArrayList<LinkDto>();
	
	private List<LinkDto> links = new ArrayList<LinkDto>();

	public BookResponseDto(Book book) {

		this.isbn = book.getIsbn();
		this.title = book.getTitle();
		this.publication_date = book.getPublication_date();
		this.language = book.getLanguage();
		this.num_pages = book.getNum_pages();
		this.status = book.getStatus();		
		
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublication_date() {
		return publication_date;
	}

	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getNum_pages() {
		return num_pages;
	}

	public void setNum_pages(int num_pages) {
		this.num_pages = num_pages;
	}


	
	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	public void addAuthor(LinkDto link) {
		authors.add(link);
	}

	public List<LinkDto> getAuthors() {
		return authors;
	}

	public void setAuthors(List<LinkDto> authors) {
		this.authors = authors;
	}	

	public void addLink(LinkDto link) {
		links.add(link);
	}

	/**
	 * @return the links
	 */
	public List<LinkDto> getLinks() {
		return links;
	}

	/**
	 * @param links
	 *            the links to set
	 */
	public void setLinks(List<LinkDto> links) {
		this.links = links;
	}

	/**
	 * @param reviews
	 *            the reviews to set
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * @return the reviews
	 */
	public List<Review> getReviews() {
		return reviews;
	}

}
