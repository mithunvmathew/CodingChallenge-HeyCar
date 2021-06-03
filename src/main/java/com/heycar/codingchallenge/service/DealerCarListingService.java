package com.heycar.codingchallenge.service;


import com.heycar.codingchallenge.dto.CsvUploadListingDto;
import com.heycar.codingchallenge.dto.JsonUploadListingDto;
import com.heycar.codingchallenge.mapper.CsvListingToCarListingEntityMapper;
import com.heycar.codingchallenge.mapper.DealerCSVToCsvListingDtoMapper;
import com.heycar.codingchallenge.mapper.JsonListingDtoToCarListingEntityMapper;
import com.heycar.codingchallenge.model.CarListing;
import com.heycar.codingchallenge.model.CarListingId;
import com.heycar.codingchallenge.repository.CarListingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DealerCarListingService implements CarListingService {

    private final DealerCSVToCsvListingDtoMapper dealerCSVToCsvListingDtoMapper;
    private final CsvListingToCarListingEntityMapper csvListingToCarListingEntityMapper;
    private final JsonListingDtoToCarListingEntityMapper jsonListingDtoToCarListingEntityMapper;
    private final CarListingRepository carListingRepository;

    @Override
    public Integer uploadCsVToCarListing(MultipartFile csv, String dealerId) {
        log.debug("dealer :{} listing imorting", dealerId);
        List<CsvUploadListingDto> csvUploadListingDtoList = dealerCSVToCsvListingDtoMapper.csvToDto(csv,
                CsvUploadListingDto.builder().build());
        List<CarListing> carListingList = csvUploadListingDtoList.stream()
                .map(csvUploadListingDto -> {
                    CarListing entity = csvListingToCarListingEntityMapper.toEntity(csvUploadListingDto);
                    entity.setCarListingId(CarListingId.builder()
                            .dealerId(dealerId).code(csvUploadListingDto.getCode()).build());
                    return entity;
                }).collect(Collectors.toList());
        log.debug("delaer");
        return carListingRepository.saveAll(carListingList).size();
    }

    @Override
    public Integer uploadJsonListToCarListing(List<JsonUploadListingDto> uploadList, String dealerId) {
        List<CarListing> carListingList = uploadList.stream()
                .map(jsonUploadListingDto -> {
                    CarListing entity = jsonListingDtoToCarListingEntityMapper.toEntity(jsonUploadListingDto);
                    entity.setCarListingId(CarListingId.builder()
                            .dealerId(dealerId).code(jsonUploadListingDto.getCode()).build());
                    return entity;
                }).collect(Collectors.toList());
        return carListingRepository.saveAll(carListingList).size();
    }
}
