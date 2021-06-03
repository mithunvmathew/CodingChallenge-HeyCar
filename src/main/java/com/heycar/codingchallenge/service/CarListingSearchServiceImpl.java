package com.heycar.codingchallenge.service;


import com.heycar.codingchallenge.model.CarListing;
import com.heycar.codingchallenge.repository.CarListingRepository;
import com.heycar.codingchallenge.repository.CarSearchSpecification;
import com.heycar.codingchallenge.repository.SearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarListingSearchServiceImpl implements CarListingSearchService {
    private final CarListingRepository carListingRepository;

    @Transactional
    @Override
    public Page<CarListing> getSearchList(List<SearchCriteria> searchCriteriaList, int page, int pageSize) {

        log.debug("Size of searchCriteriaList:{}", searchCriteriaList.size());
        if (searchCriteriaList.isEmpty()) {
            return carListingRepository.findAll(PageRequest.of(page, pageSize));
        }
        Specification<CarListing> specification = null;
        for (SearchCriteria criteria : searchCriteriaList) {
            if (specification == null) {
                specification = Specification.where(new CarSearchSpecification(criteria));
            } else {
                specification= specification.and(new CarSearchSpecification(criteria));
            }
        }

        return carListingRepository.findAll(specification, PageRequest.of(page, pageSize));
    }
}
