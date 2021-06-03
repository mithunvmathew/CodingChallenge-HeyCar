package com.heycar.codingchallenge.repository;


import com.heycar.codingchallenge.model.CarListing;
import com.heycar.codingchallenge.model.CarListingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarListingRepository extends JpaRepository<CarListing, CarListingId>,
        JpaSpecificationExecutor<CarListing> {
}
