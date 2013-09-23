package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe.library.exception.HTTPClientException;

public class Book {

	private long isbn;

	@NotEmpty
	private String title;

	@NotEmpty
	@JsonProperty("publication-date")
	private String publication_date;

	private String language;

	@JsonProperty("num-pages")
	private int num_pages;

	private BookStatus status = BookStatus.available;

	private List<Review> reviews = new ArrayList<Review>();

	@NotEmpty
	private List<Author> authors = new ArrayList<Author>();

	/**
	 * @return the isbn
	 */
	public long getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn
	 *            the isbn to set
	 */
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
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

	public void setStatus(String status) {

		if (!BookStatus.contains(status)) {
			throw new HTTPClientException(
					"status does not match. It should be one of available, checked-out, in-queue, or lost.");

		}

		this.status = BookStatus.valueOf(status);
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

}
