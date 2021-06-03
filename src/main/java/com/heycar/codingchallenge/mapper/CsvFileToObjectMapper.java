package com.heycar.codingchallenge.mapper;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
public interface CsvFileToObjectMapper<T> {

    /**
     * This is only for future development. If we have more than one csv type , we can implement the same mapper
     * and distinguish the mapper via dealCsvType
     *
     * @return String
     */
    String getDealerCsvType();

    @SneakyThrows
    default List<T> csvToDto(MultipartFile csvFile, T t) {

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader objectReader = csvMapper.readerFor(t.getClass()).with(schema);

        List<T> objectList = new ArrayList<>();
        try (Reader reader = new FileReader(convertMultiPartToFile(csvFile))) {
            MappingIterator<T> mappingIterator = objectReader.readValues(reader);
            while (mappingIterator.hasNext()) {
                T obj = mappingIterator.next();
                doValidate(obj);
                objectList.add(obj);
            }
        }
        return objectList;

    }

    default void doValidate(T t) {
        // Need to do validation in the implemented class;
    }

    @SneakyThrows
    static File convertMultiPartToFile(MultipartFile file) {
        File tempFile = new File("tempFile");
        FileOutputStream fos = new FileOutputStream(new File("tempFile"));
        fos.write(file.getBytes());
        fos.close();
        return tempFile;
    }

}
