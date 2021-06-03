package com.heycar.codingchallenge.mapper;


import com.heycar.codingchallenge.dto.JsonUploadListingDto;
import com.heycar.codingchallenge.model.CarListing;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JsonListingDtoToCarListingEntityMapper {

    CarListing toEntity(JsonUploadListingDto dto);
}

