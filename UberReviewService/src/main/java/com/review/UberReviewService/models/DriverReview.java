package com.review.UberReviewService.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name="driverReviewId")
public class DriverReview extends Review{

    private String driverReviewComment;
}

//Cannot use identity column key generation with <union-subclass> mapping for: com.review.UberReviewService.models.DriverReview