CREATE TABLE rider (
  id BIGINT AUTO_INCREMENT NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   name VARCHAR(255) NULL,
   CONSTRAINT pk_rider PRIMARY KEY (id)
);

CREATE TABLE driver_review (
  driver_review_id BIGINT NOT NULL,
   driver_review_comment VARCHAR(255) NULL,
   CONSTRAINT pk_driverreview PRIMARY KEY (driver_review_id)
);

CREATE TABLE rider_review (
  rider_review_id BIGINT NOT NULL,
   rider_review_comment VARCHAR(255) NULL,
   rider_rating VARCHAR(255) NULL,
   CONSTRAINT pk_riderreview PRIMARY KEY (rider_review_id)
);

CREATE TABLE driver (
  id BIGINT AUTO_INCREMENT NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   name VARCHAR(255) NULL,
   license_number VARCHAR(255) NULL,
   CONSTRAINT pk_driver PRIMARY KEY (id)
);

CREATE TABLE booking (
  id BIGINT AUTO_INCREMENT NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   review_id BIGINT NULL,
   booking_status VARCHAR(255) NULL,
   start_time datetime NULL,
   end_time datetime NULL,
   total_distance BIGINT NULL,
   driver_id BIGINT NULL,
   rider_id BIGINT NULL,
   CONSTRAINT pk_booking PRIMARY KEY (id)
);

CREATE TABLE booking_review (
  id BIGINT NOT NULL,
   created_at datetime NOT NULL,
   updated_at datetime NOT NULL,
   content VARCHAR(255) NOT NULL,
   rating DOUBLE NULL,
   CONSTRAINT pk_bookingreview PRIMARY KEY (id)
);


ALTER TABLE booking ADD CONSTRAINT FK_BOOKING_ON_DRIVER FOREIGN KEY (driver_id) REFERENCES driver (id);

ALTER TABLE booking ADD CONSTRAINT FK_BOOKING_ON_REVIEW FOREIGN KEY (review_id) REFERENCES booking_review (id);

ALTER TABLE booking ADD CONSTRAINT FK_BOOKING_ON_RIDER FOREIGN KEY (rider_id) REFERENCES rider (id);

ALTER TABLE driver ADD CONSTRAINT uc_driver_license_number UNIQUE (license_number);

ALTER TABLE driver_review ADD CONSTRAINT FK_DRIVERREVIEW_ON_DRIVERREVIEWID FOREIGN KEY (driver_review_id) REFERENCES booking_review (id);

ALTER TABLE rider_review ADD CONSTRAINT FK_RIDERREVIEW_ON_RIDERREVIEWID FOREIGN KEY (rider_review_id) REFERENCES booking_review (id);
