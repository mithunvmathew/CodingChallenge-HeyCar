package com.heycar.codingchallenge.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class CsvUploadListingDto {

    @NotEmpty
    String code;

    @NotEmpty
    @JsonAlias("make/model")
    String makeAndModel;

    @NotEmpty
    @JsonAlias("power-in-ps")
    String power;

    @NotEmpty
    Integer year;

    @NotEmpty
    String color;

    @NotNull
    Double price;
}
