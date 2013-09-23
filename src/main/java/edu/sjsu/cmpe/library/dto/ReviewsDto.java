package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.library.domain.Review;

/**
 * @author scott
 * 
 */
public class ReviewsDto {
	private List<Review> reviews = new ArrayList<Review>();

	private List<LinkDto> links = new ArrayList<LinkDto>();

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
