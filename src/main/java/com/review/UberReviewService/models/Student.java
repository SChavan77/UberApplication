package com.review.UberReviewService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//Just for practice
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends BaseModel{

    //id is coming from BaseModel
    private String name;
    private String roolNo;

    @ManyToMany
    @JoinTable(
            name="Course_Students",
            joinColumns= @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )
             //column of the class where we are writing @JoinTable ei,Student
    private List<Course> courses=new ArrayList<>();
}
/*

OVERALL
Hibernate: create table booking (id bigint not null auto_increment, created_at datetime(6) not null, updated_at datetime(6) not null, booking_status enum ('ASSIGNING_DRIVER','CAB_ARRIVED','CANCELLED','COMPLETED','IN_RIDE','SCHEDULED'), end_time datetime(6), start_time datetime(6), total_distance bigint, driver_id bigint, review_id bigint, rider_id bigint, primary key (id)) engine=InnoDB
Hibernate: create table booking_review (id bigint not null auto_increment, created_at datetime(6) not null, updated_at datetime(6) not null, content varchar(255) not null, rating float(53), primary key (id)) engine=InnoDB
Hibernate: create table driver (id bigint not null auto_increment, created_at datetime(6) not null, updated_at datetime(6) not null, license_number varchar(255), name varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table driver_review (driver_review_comment varchar(255), driver_review_id bigint not null, primary key (driver_review_id)) engine=InnoDB
Hibernate: create table rider (id bigint not null auto_increment, created_at datetime(6) not null, updated_at datetime(6) not null, name varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table rider_review (rider_rating varchar(255), rider_review_comment varchar(255), rider_review_id bigint not null, primary key (rider_review_id)) engine=InnoDB
Hibernate: alter table booking drop index UK2c57floc70nhp4ehcsn9ctr71
Hibernate: alter table booking add constraint UK2c57floc70nhp4ehcsn9ctr71 unique (review_id)
Hibernate: alter table driver drop index UK9yq25nknyh5y5gihylet1ypy9
Hibernate: alter table driver add constraint UK9yq25nknyh5y5gihylet1ypy9 unique (license_number)
Hibernate: alter table booking add constraint FKd3n9h18mu017cxfopghwkri7s foreign key (driver_id) references driver (id)
Hibernate: alter table booking add constraint FKh1stionm0jgsyfg7fv98trhjj foreign key (review_id) references booking_review (id)
Hibernate: alter table booking add constraint FK4sr0ragyfupkbymd84g9sip04 foreign key (rider_id) references rider (id)
Hibernate: alter table driver_review add constraint FKnu3riro2219q477fo4ipa1nqk foreign key (driver_review_id) references booking_review (id)
Hibernate: alter table rider_review add constraint FKgkdkw7827wy2uhf5frej3t6pt foreign key (rider_review_id) references booking_review (id)


Hibernate: create table course (id bigint not null auto_increment, created_at datetime(6) not null, updated_at datetime(6) not null, name varchar(255), rool_no varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table course_students (student_id bigint not null, course_id bigint not null) engine=InnoDB
Hibernate: create table student (id bigint not null auto_increment, created_at datetime(6) not null, updated_at datetime(6) not null, name varchar(255), rool_no varchar(255), primary key (id)) engine=InnoDB
Hibernate: alter table course_students add constraint FKgut5xj4l8sk6hg3l0t2su2pnc foreign key (course_id) references course (id)
Hibernate: alter table course_students add constraint FK61ry13vjip2yrapfiag3mt9pq foreign key (student_id) references student (id)
*/