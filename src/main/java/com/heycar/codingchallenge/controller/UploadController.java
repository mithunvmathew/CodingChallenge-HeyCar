package com.heycar.codingchallenge.controller;

import com.heycar.codingchallenge.dto.JsonUploadListingDto;
import com.heycar.codingchallenge.model.CarListing;
import com.heycar.codingchallenge.repository.CarListingRepository;
import com.heycar.codingchallenge.service.CarListingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UploadController {
    private final CarListingService carListingService;
    private final CarListingRepository repository;

    @PostMapping("/upload_csv/{dealerId}")
    public ResponseEntity<String> uploadDealerCsvListing(@PathVariable String dealerId,
                                                         @RequestParam("csvFile") MultipartFile csvFile) {
        return new ResponseEntity<>("Total Listing Uploaded: "
                + carListingService.uploadCsVToCarListing(csvFile, dealerId), HttpStatus.OK);
    }

    @PostMapping("/vehicle_listing/{dealerId}")
    public ResponseEntity<String> uploadDealerJsonListing(@PathVariable String dealerId,
                                                          @Valid @RequestBody List<JsonUploadListingDto> jsonUploadListingList) {
        return new ResponseEntity<>("Total Listing Uploaded: "
                + carListingService.uploadJsonListToCarListing(jsonUploadListingList, dealerId), HttpStatus.OK);
    }

    @GetMapping("test")
    public List<CarListing> getAccountsByType() {
        return repository.findAll();

    }
}
