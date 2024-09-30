package com.review.UberReviewService.repositories;

import com.review.UberReviewService.models.Booking;
import com.review.UberReviewService.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking,Long> {
    //@Query("select * from ")
    Optional<List<Booking>> findAllByDriverId(Long driverId);

}
