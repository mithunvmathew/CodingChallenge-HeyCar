package com.heycar.integrationTest.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@UtilityClass
@Slf4j
public class FileUtils {

    public static String getResourceFileAsString(String fileName) {
        try (InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                log.warn("Initializing {} with empty value", fileName);
                return StringUtils.EMPTY;
            }
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        } catch (IOException e) {
            log.error("Error during reading file " + fileName, e);
            return StringUtils.EMPTY;
        }
    }
}