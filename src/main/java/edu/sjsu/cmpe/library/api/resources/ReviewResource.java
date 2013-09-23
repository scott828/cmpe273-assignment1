package edu.sjsu.cmpe.library.api.resources;


import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.exception.HTTPClientException;
import edu.sjsu.cmpe.library.model.LibraryModel;
import edu.sjsu.cmpe.library.util.SequenceGenerator;

@Path("/v1/books/{isbn}/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {

	public ReviewResource() {
		// do nothing
	}

	@GET
	@Path("/{id}")
	@Timed(name = "view-review")
	public ReviewDto getReview(@PathParam("isbn") long isbn,
			@PathParam("id") long id) throws Exception{

		if (!LibraryModel.getBookRepository().containsKey(isbn)) {			
			throw new HTTPClientException("isbn does not match!");
		}
		
		Book book = LibraryModel.getBookRepository().get(isbn);
		List<Review> reviews = book.getReviews();

		Review review = null;

		for (Review temp : reviews) {
			if (temp.getId() == id) {
				review = temp;
				break;
			}
		}

		if (null == review) {
			if (!LibraryModel.getBookRepository().containsKey(isbn)) {			
				throw new HTTPClientException("review id not matched");
			}
		}

		ReviewDto reviewResponse = new ReviewDto(review);
		reviewResponse.setReview(review);
		reviewResponse.addLink(new LinkDto("view-review", "/books/"
				+ book.getIsbn() + "/reviews/" + review.getId(), "GET"));

		return reviewResponse;
	}
	
	@GET
	@Timed(name = "view-reviews")
	public ReviewsDto getAllReview(@PathParam("isbn") long isbn ) throws Exception{

	
		if (!LibraryModel.getBookRepository().containsKey(isbn)) {			
			throw new HTTPClientException("isbn does not match!");
		}
		
		Book book = LibraryModel.getBookRepository().get(isbn);
		List<Review> reviews = book.getReviews();

		
		ReviewsDto reviewResponse = new ReviewsDto();
		reviewResponse.setReviews(reviews);

		return reviewResponse;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "create-review")
	public Response createReview(@PathParam("isbn") long isbn, @Valid Review review) throws Exception {

		if (!LibraryModel.getBookRepository().containsKey(isbn)) {			
			throw new HTTPClientException("isbn does not match!");
		}
		
		long id = SequenceGenerator.nextReviewId();
		review.setId(id);

		Book book = LibraryModel.getBookRepository().get(isbn);
		book.getReviews().add(review);
		LibraryModel.getBookRepository().put(isbn, book);

		
		
		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-review", "/books/"
				+ book.getIsbn() + "/reviews/" + review.getId(), "GET"));

				
		return Response.status(201).entity(links).build();

	}

}
