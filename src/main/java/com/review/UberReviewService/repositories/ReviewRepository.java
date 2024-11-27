package com.review.UberReviewService.repositories;

import com.review.UberReviewService.models.Driver;
import com.review.UberReviewService.models.Review;
import com.review.UberReviewService.models.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //To fetch the review <3 or >4
    Integer countAllByRatingIsLessThanEqual(Integer givenRating);

    List<Review> findAllByRatingIsLessThanEqual(Integer givenRating);

    List<Review> findAllByCreatedAtBefore(Date date);

    @Query("SELECT b.review from Booking b where b.Id = :bookingId")
    Review findReviewByBookingId(Long bookingId);
}

/*
to in BookingRepo
@Query("SELECT new com.review.UberReviewService.repositories(r,p,d) from booking b inner join Rider p inner join Driver d ")
custom findDriverPassengerReviewByBookingId(Long bookingId);
class custom{

    public Review review;
    public Rider rider;
    public Driver driver;

    public custom(Review review, Rider rider, Driver driver) {
        this.review = review;
        this.rider = rider;
        this.driver = driver;
    }
}*/



//@Query(value = "SELECT r FROM Booking b INNER JOIN Review r on b.review_id=r.Id where b.Id = :bookingId",nativeQuery = true)
  /* @Query("select r from Booking b inner join Review r where b.id = :bookingId")
    Review findReviewByBookingId(Long bookingId);*/
