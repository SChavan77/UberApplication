package com.review.UberReviewService.services;

import com.review.UberReviewService.models.*;
import com.review.UberReviewService.repositories.BookingRepo;
import com.review.UberReviewService.repositories.DriverRepository;
import com.review.UberReviewService.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements CommandLineRunner {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookingRepo bookingRepository;

    @Autowired
    DriverRepository driverRepository;

    @Override
    public void run(String... args) throws Exception {

       /* Review review= Review.builder()
                .content("Good").rating(5.4)   //.createdAt(new Date()).updatedAt(new Date())
                .build();

        Booking booking=Booking.builder()
                .review(review)
                .endTime(new Date()).build();

        bookingRepository.save(booking); //so booking doesn't depend on review. But still throws an error in this sequence.why?
       // reviewRepository.save(review); //Due to cascade.persist feature.

        //In Booking table, | review_id | bigint  | YES  | UNI | NULL  <--review_id field can be null(YES)
        List<Review> list=reviewRepository.findAll();*/

      /* Optional<Driver> driver=driverRepository.findByIdAndLicenseNumber(1L,"DL12121");
       if(driver.isPresent())
           System.out.println(driver.get().getName());
       Optional<List<Booking>> bookings =bookingRepository.findAllByDriverId(1L);
        if(bookings.isPresent()){
            for(Booking b: bookings.get())
                System.out.println(b.getBookingStatus());
        }*/

       /* Optional<Driver> driver=driverRepository.findById(1L);
        Optional<Booking> b=bookingRepository.findById(1L);
        if(driver.isPresent()){
            System.out.println(driver.get().getName());
            List<Booking> bookings=driver.get().getBookings();
                for(Booking booking: bookings){
                    System.out.println(booking.getId());
                }
        }*/

        Optional<Driver> driver=driverRepository.rawFindByIdAndLicenseNumber(1L,"DL12121");
        if(driver.isPresent())
            System.out.println(driver.get().getName());

        Optional<Driver> driver1=driverRepository.rawFindByIdAndLicenseNumberV2(1L,"DL12121");
        if(driver1.isPresent())
            System.out.println(driver1.get().getName());

        Optional<DriverInfoI> driver2=driverRepository.rawFindByIdAndLicenseNumberV3(1L,"DL12121");
       if(driver2.isPresent())
            System.out.println(driver2.get().getName());
    }
}
/*
*  bookingRepository.save(booking); //so booking doesn't depend on review. But still throws an error in this sequence.why?
        reviewRepository.save(review);

    Why? At the SQL level, while BookingRepo is trying to persist booking. It needs review entry.
   But review entry is not still persisted. so throws an error.
   *
   *
   reviewRepository.save(review);
   bookingRepository.save(booking);
   At the SQL level, while BookingRepo is trying to persist booking. It needs review entry.
   It fetches from already peristed review entry from review table and save it in Booking table.

    Here manually we're handling the sequence of saving.
    Apply Cascading !!!
    IG account deleted, all the post should also to be deleted.
    so use: in Booking . Only the main table save is enough. all the dependent tables entry will be persisted automatically before it's
    *  @OneToOne(cascade = CascadeType.PERSIST)
        private Review review;

    NO save to repo for review is needed now.
    *REMOVE-
    If we delete booking, all the review associated reviews will be removed.
    * REFRESH-
    If we reload booking, reviews associated with it also get reloaded.
* */