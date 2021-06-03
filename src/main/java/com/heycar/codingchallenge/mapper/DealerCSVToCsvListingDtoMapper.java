package com.heycar.codingchallenge.mapper;


import com.heycar.codingchallenge.dto.CsvUploadListingDto;
import org.springframework.stereotype.Component;

@Component
public class DealerCSVToCsvListingDtoMapper  implements CsvFileToObjectMapper{
    @Override
    public String getDealerCsvType() {
        return "DEALER_CSV";
    }

    public void doValidate(CsvUploadListingDto csvUploadListingDto) {
      //  TODO need to implement data validation for the Dtos from csv file
    }
}
