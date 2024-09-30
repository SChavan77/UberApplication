package com.review.UberReviewService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name="riderReviewId")
public class RiderReview extends Review{

    private String riderReviewComment;
    private String riderRating;
}
