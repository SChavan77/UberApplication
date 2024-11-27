package com.review.UberReviewService.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking extends BaseModel {

       /* @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "booking") //all the dependent entries(review entry) will be peristed 1st and then the dependent table entry.
        private Review review; //1:1 relationship between booking & review //cascade={CascadeType.PERSIST,CascadeType.REMOVE}
        */
        //Changed to Booking has many reviews relation
        @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.LAZY) //all the dependent entries(review entry) will be peristed 1st and then the dependent table entry.
        @JoinColumn(name = "review_id", referencedColumnName = "id", nullable = true)
        private Review review; //1:1 relationship between booking & review //cascade={CascadeType.PERSIST,CascadeType.REMOVE}


        //To specify it as a enum attribute. If not specified, it takes default ORDINAL
        @Enumerated(value= EnumType.STRING) //By default(ORDINAL) tinyInt it takes
        private BookingStatus bookingStatus;

        @Temporal(value=TemporalType.TIMESTAMP)
        private Date startTime;

        @Temporal(value=TemporalType.TIMESTAMP)
        private Date endTime;

        private Long totalDistance;

        @ManyToOne (fetch = FetchType.LAZY)//many bookings has 1 driver; made it Lazy due to FETCHMODE impl
        private Driver driver; //every book has a driver. Currently dirver is in 1:N state, so to needle down, we need to do N:1 to booking

        @ManyToOne(fetch = FetchType.LAZY) //made it Lazy due to FETCHMODE impl
        private Rider rider;
}

/*
*id of Review -> review_id of Booking
* Hibernate: alter table booking add column review_id bigint
Hibernate: create table driver_review (driver_review_comment varchar(255), driver_review_id bigint not null, primary key (driver_review_id)) engine=InnoDB
Hibernate: create table rider_review (rider_rating varchar(255), rider_review_comment varchar(255), rider_review_id bigint not null, primary key (rider_review_id)) engine=InnoDB
Hibernate: alter table booking drop index UK2c57floc70nhp4ehcsn9ctr71
Hibernate: alter table booking add constraint UK2c57floc70nhp4ehcsn9ctr71 unique (review_id)
Hibernate: alter table booking add constraint FKh1stionm0jgsyfg7fv98trhjj foreign key (review_id) references booking_review (id)
Hibernate: alter table driver_review add constraint FKnu3riro2219q477fo4ipa1nqk foreign key (driver_review_id) references booking_review (id)
Hibernate: alter table rider_review add constraint FKgkdkw7827wy2uhf5frej3t6pt foreign key (rider_review_id) references booking_review (id)
* */

