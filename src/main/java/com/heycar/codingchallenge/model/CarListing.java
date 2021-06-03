package com.heycar.codingchallenge.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "car_listing")
public class CarListing {

    @EmbeddedId
    private CarListingId carListingId;

    @Column
    String make;

    @Column
    String model;

    @Column
    String power;

    @Column
    Integer year;

    @Column
    String color;

    @Column
    Double price;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private Date modifiedAt;
}
