package edu.sjsu.cmpe.library.api.resources;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.exception.HTTPClientException;
import edu.sjsu.cmpe.library.model.LibraryModel;


@Path("/v1/books/{isbn}/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

	public AuthorResource() {
		// do nothing
	}

	@GET
	@Path("/{id}")
	@Timed(name = "view-author")
	public AuthorDto getAuthor(@PathParam("isbn") long isbn,
			@PathParam("id") long id) throws Exception {

		if (!LibraryModel.getBookRepository().containsKey(isbn)) {			
			throw new HTTPClientException("isbn does not match!");
		}

		Book book = LibraryModel.getBookRepository().get(isbn);
		List<Author> authors = book.getAuthors();

		Author author = null;

		for (Author temp : authors) {
			if (temp.getId() == id) {
				author = temp;
				break;
			}
		}

		if (null == author) {
			throw new HTTPClientException("author id not matched");
		}

		AuthorDto authorResponse = new AuthorDto(author);
		authorResponse.addLink(new LinkDto("view-author", "/books/"
				+ book.getIsbn() + "/authors/" + author.getId(), "GET"));

		return authorResponse;
	}

	@GET
	@Timed(name = "view-authors")
	public AuthorsDto getAllAuthor(@PathParam("isbn") long isbn) throws Exception {

		if (!LibraryModel.getBookRepository().containsKey(isbn)) {			
			throw new HTTPClientException("isbn does not match!");
		}

		Book book = LibraryModel.getBookRepository().get(isbn);
		List<Author> authors = book.getAuthors();

		AuthorsDto response = new AuthorsDto();
		response.setAuthors(authors);

		return response;
	}

}
