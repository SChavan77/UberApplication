package com.review.UberReviewService.repositories;

import com.review.UberReviewService.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    //Spring JPA these basic user defined methods and construct a query.
    Optional<Driver> findByIdAndLicenseNumber(Long id, String lNumber);

    @Query(nativeQuery = true, value = "SELECT * FROM Driver where id= :id AND license_Number= :lNum")
        //error thrown at RT, use iff only complex queries.
    Optional<Driver> rawFindByIdAndLicenseNumber(@Param("id") Long id, @Param("lNum") String lNumber); //After Java 8+ @param to be used.

    @Query("SELECT d FROM Driver d where d.id= :id AND d.licenseNumber= :lNumber") //if any error, CTErr comes. Better, robust
    Optional<Driver> rawFindByIdAndLicenseNumberV2(Long id, String lNumber);

    /* This won't work
    @Query("SELECT d.id,d.name FROM Driver d where d.id= :id AND d.licenseNumber= :lNumber")//if any error, CTErr comes. Better, robust
    Optional<Driver> rawFindByIdAndLicenseNumberV3(Long id, String lNumber);
    */

   /* @Query("SELECT d.id,d.name FROM Driver d where d.id= :id AND d.licenseNumber= :lNumber")
    Optional<DriverInfoI> rawFindByIdAndLicenseNumberV3(Long id, String lNumber); //Using DriverInofI Interface*/

  /*@Query("SELECT d.id,d.name FROM Driver d where d.id= :id AND d.licenseNumber= :lNumber")
    Optional<CustomDriver> rawFindByIdAndLicenseNumberV4(Long id, String lNumber); //Using DTO*/

    List<Driver> findAllByIdIn(List<Long> d_Ids);
}
//Use @Param for query method parameters, or when on Java 8+ use the javac flag -parameters
/**
 *
 * In RAW sql, column name should be given as exactly it is there in Table. eg; license_Number
 * In Hibernate,need not
 *
 *
 * Should not use select * in Hibernate Query.
 * use-  Select d from Driver d... OR Select d from Driver as d
 */

