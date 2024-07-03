package com.dataprocessing.multithreadedcsvimporter;

import lombok.RequiredArgsConstructor;

import java.io.File;
import org.apache.commons.csv.CSVFormat;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;
import com.dataprocessing.multithreadedcsvimporter.customer.csvimport.CustomerImportProcessor;
import com.dataprocessing.multithreadedcsvimporter.dataprocessing.CSVImporter;
import com.dataprocessing.multithreadedcsvimporter.dataprocessing.DefaultCSVImporter;

@SpringBootApplication
@RequiredArgsConstructor
public class MultithreadedCsvImporterApplication {

    private final CustomerImportProcessor customerImportProcessor;

    public static void main(String[] args) {
        SpringApplication.run(MultithreadedCsvImporterApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(CSVImporter csvImporter) {
        return (args) -> {
            File file = ResourceUtils.getFile("classpath:import/customers-data.csv");
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
              .setHeader("email", "first_name", "last_name", "city", "country", "phone1")
              .setSkipHeaderRecord(true)
              .build();
            csvImporter.setCsvFormat(csvFormat);
            csvImporter.startImport(file);
        };
    }

    @Bean
    public CSVImporter csvImporter() {
        return new DefaultCSVImporter(customerImportProcessor);
    }

}
