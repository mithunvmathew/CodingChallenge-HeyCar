package com.heycar.codingchallenge.service;


import com.heycar.codingchallenge.dto.JsonUploadListingDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarListingService {

    Integer uploadCsVToCarListing(MultipartFile csv, String dealerId);

    Integer uploadJsonListToCarListing(List<JsonUploadListingDto> uploadList, String dealerId);
}
