package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;

@JsonPropertyOrder(alphabetic = true)
public class AuthorDto extends LinksDto {
	private Author author;

	/**
	 * @param review
	 */
	public AuthorDto(Author author) {
		super();
		this.author = author;
	}

	/**
	 * @return the review
	 */
	public Author getAuthor() {
		return author;
	}

	/**
	 * @param review
	 *            the review to set
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}
}
