package com.ness.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ness.ms.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
