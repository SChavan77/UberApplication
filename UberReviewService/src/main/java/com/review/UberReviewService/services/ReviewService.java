package com.review.UberReviewService.services;

import com.review.UberReviewService.models.*;
import com.review.UberReviewService.repositories.BookingRepo;
import com.review.UberReviewService.repositories.DriverRepository;
import com.review.UberReviewService.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewService implements CommandLineRunner {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookingRepo bookingRepository;

    @Autowired
    DriverRepository driverRepository;

    @Override
    @Transactional
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

      /*  Optional<Driver> driver=driverRepository.rawFindByIdAndLicenseNumber(1L,"DL12121");
        if(driver.isPresent())
            System.out.println(driver.get().getName());

        Optional<Driver> driver1=driverRepository.rawFindByIdAndLicenseNumberV2(1L,"DL12121");
        if(driver1.isPresent())
            System.out.println(driver1.get().getName());

        Optional<DriverInfoI> driver2=driverRepository.rawFindByIdAndLicenseNumberV3(1L,"DL12121");
       if(driver2.isPresent())
            System.out.println(driver2.get().getName());
    */

        //(1a)To demo:
        //(1b) one solution for N+1 Issue, using JPA methods
      // List<Long> driverIds=new ArrayList<>(List.of(1L,2L));
      // List<Driver> drivers=driverRepository.findAllByIdIn(driverIds);
      //  List<Booking> bookings=bookingRepository.findAllByDriverIn(drivers);

        //(2)To demo N+1 Problems
        //List<Long> driverIds=new ArrayList<>(List.of(1L,2L,3L,4L,6L));
        //List<Driver> drivers=driverRepository.findAllByIdIn(driverIds);
        //for(Driver d : drivers){
            //  List<Booking> bookings= d.getBookings();
            //    bookings.forEach(b->System.out.println(b.getId()));
        // }
            /*
Hibernate: select d1_0.id,d1_0.created_at,d1_0.license_number,d1_0.name,d1_0.updated_at from driver d1_0 where d1_0.id in (?,?,?,?,?)
Hibernate: select b1_0.driver_id,b1_0.id,b1_0.booking_status,b1_0.created_at,b1_0.end_time,b1_0.review_id,r2_0.id,r2_0.created_at,r2_0.name,r2_0.updated_at,b1_0.start_time,b1_0.total_distance,b1_0.updated_at from booking b1_0 left join rider r2_0 on r2_0.id=b1_0.rider_id where b1_0.driver_id=?
Hibernate: select d1_0.id,d1_0.created_at,d1_0.license_number,d1_0.name,d1_0.updated_at from driver d1_0 where d1_0.id=?
             */

        //(3) One more solution for N+1 Issue: Using Fetch Mode. Annotate Booking field @Fetch in Driver class.
       List<Long> driverIds=new ArrayList<>(List.of(1L,2L,3L,4L,6L));
        List<Driver> drivers= driverRepository.findAllByIdIn(driverIds);
        for(Driver d : drivers){
            //System.out.println(d.getBookings().size());
            List<Booking> bookings= d.getBookings();
            bookings.forEach(b->System.out.println(b.getId()));
        }
    }
}
/*
*  bookingRepository.save(booking); //so booking doesn't depend on review. But still throws an error in this sequence.why?
        reviewRepository.save(review);

   Why? At the SQL level, while BookingRepo is trying to persist booking. It needs review entry.
   But review entry is not still persisted. so throws an error.

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

    * //(1a)To demo N+1 Problems
    select d1_0.id,d1_0.created_at,d1_0.license_number,d1_0.name,d1_0.updated_at from driver d1_0 where d1_0.id in (?,?)
    select b1_0.id,b1_0.booking_status,b1_0.created_at,b1_0.driver_id,b1_0.end_time,b1_0.review_id,b1_0.rider_id,b1_0.start_time,b1_0.total_distance,b1_0.updated_at from booking b1_0 where b1_0.driver_id in (?,?)
    select d1_0.id,d1_0.created_at,d1_0.license_number,d1_0.name,d1_0.updated_at from driver d1_0 where d1_0.id=?
    select r1_0.id,r1_0.created_at,r1_0.name,r1_0.updated_at from rider r1_0 where r1_0.id=?
    select r1_0.id,r1_0.created_at,r1_0.name,r1_0.updated_at from rider r1_0 where r1_0.id=?

    * //(2): this below query runs for N times
     select d1_0.id,d1_0.created_at,d1_0.license_number,d1_0.name,d1_0.updated_at from driver d1_0 where d1_0.id in (?,?,?,?,?)
     select b1_0.driver_id,b1_0.id,b1_0.booking_status,b1_0.created_at,b1_0.end_time,b1_0.review_id,r2_0.id,r2_0.created_at,r2_0.name,r2_0.updated_at,b1_0.start_time,b1_0.total_distance,b1_0.updated_at from booking b1_0 left join rider r2_0 on r2_0.id=b1_0.rider_id where b1_0.driver_id=?
     select d1_0.id,d1_0.created_at,d1_0.license_number,d1_0.name,d1_0.updated_at from driver d1_0 where d1_0.id=?

     Since the transaction is active and the Hibernate session remains open,
     accessing the lazy-loaded bookings collection for each Driver doesn't trigger a new session.
     This allows Hibernate to properly manage the fetching strategy, including using @Fetch(FetchMode.SUBSELECT)
     if specified, so multiple queries can be avoided.
     *
     * //(3)
    select d1_0.id,d1_0.created_at,d1_0.license_number,d1_0.name,d1_0.updated_at from driver d1_0 where d1_0.id in (?,?,?,?,?)
    select b1_0.driver_id,b1_0.id,b1_0.booking_status,b1_0.created_at,b1_0.end_time,b1_0.review_id,b1_0.rider_id,b1_0.start_time,b1_0.total_distance,b1_0.updated_at from booking b1_0 where b1_0.driver_id in (select d1_0.id from driver d1_0 where d1_0.id in (?,?,?,?,?))
    *
    * //(1b) Using transactional
    select d1_0.id,d1_0.created_at,d1_0.license_number,d1_0.name,d1_0.updated_at from driver d1_0 where d1_0.id in (?,?)
    select b1_0.id,b1_0.booking_status,b1_0.created_at,b1_0.driver_id,b1_0.end_time,b1_0.review_id,b1_0.rider_id,b1_0.start_time,b1_0.total_distance,b1_0.updated_at from booking b1_0 where b1_0.driver_id in (?,?)
 */
