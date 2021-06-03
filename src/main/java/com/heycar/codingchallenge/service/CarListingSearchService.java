package com.heycar.codingchallenge.service;


import com.heycar.codingchallenge.model.CarListing;
import com.heycar.codingchallenge.repository.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarListingSearchService {

    Page<CarListing> getSearchList(List<SearchCriteria> searchCriteriaList, int page, int pageSize);
}
