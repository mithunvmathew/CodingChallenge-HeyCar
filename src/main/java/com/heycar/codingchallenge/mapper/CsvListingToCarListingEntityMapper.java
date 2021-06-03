package com.heycar.codingchallenge.mapper;

import com.heycar.codingchallenge.dto.CsvUploadListingDto;
import com.heycar.codingchallenge.model.CarListing;
import org.mapstruct.*;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CsvListingToCarListingEntityMapper {

    CarListing toEntity(CsvUploadListingDto dto);

    @AfterMapping
    default void updateMakeAndModel(CsvUploadListingDto dto, @MappingTarget CarListing carListing) {

        List<String> mm = Arrays.asList(dto.getMakeAndModel().split("/"));
        carListing.setMake(mm.get(0));
        carListing.setModel(mm.get(1));
    }

}
