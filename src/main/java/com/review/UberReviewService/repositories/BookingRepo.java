package com.review.UberReviewService.repositories;

import com.review.UberReviewService.models.Booking;
import com.review.UberReviewService.models.Driver;
import com.review.UberReviewService.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking,Long> {

    //Optional<List<Booking>> findAllByDriverId(Long driverId);
    //List<Booking> findAllByDriverIn(List<Driver> drivers);//findAllByDriverIn: JPA method


}

/*
*
* Now we're cleaning up Review Micro service.
* Review Service is not involved in find DriverId details etc., so clean it up.
*
* Even this query goes: on property removed. Internally it fetches the relation (at compile time itself)
* @Query(value = "SELECT r FROM Booking b INNER JOIN Review r where b.Id = :bookingId")
*
* */