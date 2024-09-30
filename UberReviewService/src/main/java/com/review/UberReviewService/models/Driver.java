package com.review.UberReviewService.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends BaseModel{

    private String name;

    @Column(nullable = true, unique = true)
    private String licenseNumber;

    //1: N : driver has many bookings/reviews
    @OneToMany (mappedBy = "driver", fetch= FetchType.LAZY) //re-check
    private List<Booking> bookings=new ArrayList<>();



}
