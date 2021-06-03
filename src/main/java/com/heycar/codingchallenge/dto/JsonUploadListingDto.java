package com.heycar.codingchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonUploadListingDto {

    @NotEmpty
    String code;

    @NotEmpty
    String make;

    @NotEmpty
    String model;

    @NotEmpty
    String power;

    @NotEmpty
    Integer year;

    @NotEmpty
    String color;

    @NotNull
    Double price;
}

