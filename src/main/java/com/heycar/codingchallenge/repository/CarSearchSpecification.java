package com.heycar.codingchallenge.repository;


import com.heycar.codingchallenge.model.CarListing;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
@RequiredArgsConstructor
public class CarSearchSpecification implements Specification<CarListing> {
    private final SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(Root<CarListing> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        switch (searchCriteria.getOperator().name()) {

            case "GREATER_THAN_OR_EQUAL":
                return criteriaBuilder.greaterThanOrEqualTo(root.get(String.valueOf(searchCriteria.getKey())),
                        (Comparable) searchCriteria.getValue());
            case "LESS_THAN":
                return criteriaBuilder.lessThan(root.get(String.valueOf(searchCriteria.getKey())),
                        (Comparable) searchCriteria.getValue());
            default:
                return criteriaBuilder.equal(
                        root.get(String.valueOf(searchCriteria.getKey())), searchCriteria.getValue());
        }
    }
}
