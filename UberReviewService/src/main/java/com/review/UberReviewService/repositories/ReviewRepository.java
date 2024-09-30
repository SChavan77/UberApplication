package com.review.UberReviewService.repositories;

import com.review.UberReviewService.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
