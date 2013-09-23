package edu.sjsu.cmpe.library.api.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.BookResponseDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.exception.HTTPClientException;
import edu.sjsu.cmpe.library.model.LibraryModel;
import edu.sjsu.cmpe.library.util.SequenceGenerator;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

	public BookResource() {
		// do nothing
	}

	@GET
	@Path("/{isbn}")
	@Timed(name = "view-book")
	public BookResponseDto getBookByIsbn(@PathParam("isbn") long isbn) throws Exception {

		if (!LibraryModel.getBookRepository().containsKey(isbn)) {			
			throw new HTTPClientException("isbn does not match!");
		}

		Book book = LibraryModel.getBookRepository().get(isbn);

		BookResponseDto bookResponse = new BookResponseDto(book);

		List<Author> authorList = book.getAuthors();

		// transform author object list to author URI list
		if (authorList.size() > 0) {
			for (Author author : authorList) {
				bookResponse
						.addAuthor(new LinkDto("view-author",
								"/books/" + book.getIsbn() + "/authors/"
										+ author.getId(), "GET"));
			}

		}

		bookResponse.addLink(new LinkDto("view-book", "/books/"
				+ book.getIsbn(), "GET"));
		bookResponse.addLink(new LinkDto("update-book", "/books/"
				+ book.getIsbn(), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book", "/books/"
				+ book.getIsbn(), "DELETE"));
		bookResponse.addLink(new LinkDto("create-book", "/books/"
				+ book.getIsbn(), "POST"));

		List<Review> reviewList = book.getReviews();

		// if reviews > 0, add view-all-reviews link
		if (reviewList.size() > 0) {
			bookResponse.addLink(new LinkDto("view-all-reviews", "/books/"
					+ book.getIsbn() + "/reviews", "GET"));
		}

		return bookResponse;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "create-book")
	public Response createBook(@Valid Book book) throws Exception{

		long isbn = SequenceGenerator.nextISBN();
		book.setIsbn(isbn);

		List<Author> authors = book.getAuthors();

		for (Author author : authors) {
			author.setId(SequenceGenerator.nextAuthorId());
		}

		LibraryModel.getBookRepository().put(isbn, book);

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),
				"GET"));
		links.addLink(new LinkDto("update-book", "/books/" + book.getIsbn(),
				"PUT"));
		links.addLink(new LinkDto("delete-book", "/books/" + book.getIsbn(),
				"DELETE"));
		links.addLink(new LinkDto("create-book", "/books/" + book.getIsbn(),
				"POST"));

		return Response.status(201).entity(links).build();

	}

	@PUT
	@Path("/{isbn}")
	@Timed(name = "update-book")
	public Response updateBookStatus(@PathParam("isbn") long isbn,
			@QueryParam("status") String status) throws Exception {

		if (!LibraryModel.getBookRepository().containsKey(isbn)) {			
			throw new HTTPClientException("isbn does not match!");
		}

		Book book = LibraryModel.getBookRepository().get(isbn);


		book.setStatus(status);
		LibraryModel.getBookRepository().put(isbn, book);

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),
				"GET"));
		links.addLink(new LinkDto("update-book", "/books/" + book.getIsbn(),
				"PUT"));
		links.addLink(new LinkDto("delete-book", "/books/" + book.getIsbn(),
				"DELETE"));
		links.addLink(new LinkDto("create-book", "/books/" + book.getIsbn(),
				"POST"));

		List<Review> reviewList = book.getReviews();

		// if reviews > 0, add view-all-reviews link
		if (reviewList.size() > 0) {
			links.addLink(new LinkDto("view-all-reviews", "/books/"
					+ book.getIsbn() + "/reviews", "GET"));
		}

		return Response.ok(links).build();

	}

	@DELETE
	@Path("/{isbn}")
	@Timed(name = "delete-book")
	public Response deleteBookByIsbn(@PathParam("isbn") long isbn) throws Exception{

		if (!LibraryModel.getBookRepository().containsKey(isbn)) {			
			throw new HTTPClientException("isbn does not match!");
		}

		LibraryModel.getBookRepository().remove(isbn);

		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("create-book", "/books", "POST"));

		return Response.ok(links).build();

	}
}
