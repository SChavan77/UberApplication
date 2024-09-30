package com.review.UberReviewService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rider extends BaseModel{

    private String name;

    //property name in 'many' class(booking) should be same as here mentioned in mappedBy
    @OneToMany(mappedBy = "rider") //mapping instruction : to mention this 1:N to be mapped to rider field in Booking.
    private List<Booking> bookings=new ArrayList<>();
}
