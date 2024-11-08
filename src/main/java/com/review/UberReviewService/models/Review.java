package com.review.UberReviewService.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity (name="bookingReviewEntity")//works on the java logic layer. If @table name is not there, this will be considered for DB name+entity name
@Table(name="bookingReview") //works at the DB layer
//@EntityListeners(AuditingEntityListener.class)
//SINGLE_TABLE, TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends BaseModel{ //if nothing mentioned in name in @Entity, this is the DB name
    //Instead of Review, if different name to be used, use @Entity(name="ABC")

    /*To fix: Cannot use identity column key generation with <union-subclass> mapping for:
    com.review.UberReviewService.models.DriverReview:Change GenerationType to TABLE; hibernate_sequences*/
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column
    private Double rating;

    /* BEFORE JPA Inheritance
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP) //this annotation tells spring about the formats of Date object
    @CreatedDate //this tells to spring that only handle it for object creation.
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate //this tells to spring that only handle as and when update/modification happens.
    private Date updatedAt;
    */

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
