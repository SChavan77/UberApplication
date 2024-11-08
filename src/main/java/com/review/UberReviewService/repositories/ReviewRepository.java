package com.review.UberReviewService.repositories;

import com.review.UberReviewService.models.Driver;
import com.review.UberReviewService.models.Review;
import com.review.UberReviewService.models.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //To fetch the review <3 or >4
    Integer countAllByRatingIsLessThanEqual(Integer givenRating);

    List<Review> findAllByRatingIsLessThanEqual(Integer givenRating);

    List<Review> findAllByCreatedAtBefore(Date date);

    //@Query(value = "SELECT r FROM Booking b INNER JOIN Review r on b.review_id=r.Id where b.Id = :bookingId",nativeQuery = true)
    @Query(value = "SELECT r FROM Booking b INNER JOIN Review r on b.review=r where b.Id = :bookingId")
    Review findReviewByBookingId(Long bookingId);

    @Query("SELECT new com.review.UberReviewService.repositories() from Booking b " +
            "inner join Rider r inner join Driver d")
    custom findDriverRiderReviewByBookingId(Long bookingId); //customised field fetching: we can do like this
}


class custom{

    public Review review;
    public Rider rider;
    public Driver driver;

    public custom(Review review, Rider rider, Driver driver) {
        this.review = review;
        this.rider = rider;
        this.driver = driver;
    }
}
