package com.heycar.codingchallenge.controller;

import com.heycar.codingchallenge.enums.SearchOperator;
import com.heycar.codingchallenge.model.CarListing;
import com.heycar.codingchallenge.repository.SearchCriteria;
import com.heycar.codingchallenge.service.CarListingSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class CarListingSearchController {

    private final CarListingSearchService carListingSearchService;

    @GetMapping
    public Page<CarListing> searchCarListing(
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer yearAfter,
            @RequestParam(required = false) Integer yearBefore,
            @RequestParam(required = false) String color,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int pageSize) {
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        if (make != null) {
            searchCriteriaList.add(SearchCriteria.builder().key("make").value(make)
                    .operator(SearchOperator.EQUAL).build());
        }
        if (model != null) {
            searchCriteriaList.add(SearchCriteria.builder().key("model").value(model)
                    .operator(SearchOperator.EQUAL).build());
        }
        if (year != null) {
            searchCriteriaList.add(SearchCriteria.builder().key("year").value(year)
                    .operator(SearchOperator.EQUAL).build());
        }
        if (yearAfter != null) {
            searchCriteriaList.add(SearchCriteria.builder().key("year").value(yearAfter)
                    .operator(SearchOperator.GREATER_THAN_OR_EQUAL).build());
        }
        if (yearBefore != null) {
            searchCriteriaList.add(SearchCriteria.builder().key("year").value(yearBefore)
                    .operator(SearchOperator.LESS_THAN).build());
        }
        if (color != null) {
            searchCriteriaList.add(SearchCriteria.builder().key("color").value(color)
                    .operator(SearchOperator.EQUAL).build());
        }
        return carListingSearchService.getSearchList(searchCriteriaList, page, pageSize);
    }


}
