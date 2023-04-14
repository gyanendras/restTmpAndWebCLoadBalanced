package com.ness.ms.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reviews")
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long reviewId ;
	
	Long product_id ;
	Long user_id;
    int rating;
	String comment;
	LocalDate reviewDate;
	
	public Review() {
		super();
	}

	public Review(Long reviewId, Long product_id, Long user_id, int rating, String comment, LocalDate reviewDate) {
		super();
		this.reviewId = reviewId;
		this.product_id = product_id;
		this.user_id = user_id;
		this.rating = rating;
		this.comment = comment;
		this.reviewDate = reviewDate;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	
	
}
