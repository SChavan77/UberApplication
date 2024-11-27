package com.review.UberReviewService.repositories;

import com.review.UberReviewService.models.Booking;
import com.review.UberReviewService.models.Driver;
import com.review.UberReviewService.models.Review;
import com.review.UberReviewService.models.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    //Optional<List<Booking>> findAllByDriverId(Long driverId);
    //List<Booking> findAllByDriverIn(List<Driver> drivers);//findAllByDriverIn: JPA method

   //@Query("select r from Booking b inner join Review r where b.id= :bookingId")
   //@Query("SELECT r FROM Booking b INNER JOIN Review r on b.review_id=r.Id where b.Id = :bookingId")


}

/*class custom{

   public Review review;
   public Rider rider;
   public Driver driver;

   public custom(Review review, Rider rider, Driver driver) {
      this.review = review;
      this.rider = rider;
      this.driver = driver;
   }
}*/

/*
*
* Now we're cleaning up Review Micro service.
* Review Service is not involved in find DriverId details etc., so clean it up.
*
* Hibernate Query-
* Even this query goes: on property removed. Internally it fetches the relation (at compile time itself)
* @Query(value = "SELECT r FROM Booking b INNER JOIN Review r where b.Id = :bookingId")
*
* */