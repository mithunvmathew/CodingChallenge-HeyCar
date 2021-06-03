package com.heycar.codingchallenge.repository;

import com.heycar.codingchallenge.enums.SearchOperator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private Object key;
    private Object value;
    private SearchOperator operator;

}
