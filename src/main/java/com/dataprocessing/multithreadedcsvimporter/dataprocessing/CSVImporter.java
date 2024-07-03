package com.dataprocessing.multithreadedcsvimporter.dataprocessing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@Slf4j
@RequiredArgsConstructor
public class CSVImporter implements Importer {

    private final ImportProcessor importProcessor;
    private int itemsProcessed = 0;

    @Override
    public void startImport(final File file) {
        log.info("Import started, file: {}, size: {} MB", file.getName(), file.length() / 1024);
        long startTime = System.currentTimeMillis();

        try (Reader reader = Files.newBufferedReader(file.toPath());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord record : csvParser) {
                importProcessor.process(record);
                itemsProcessed++;
            }
        } catch (IOException e) {
            log.error("Error reading file", e);
        }

        log.info("Import finished in {} ms ({} items processed)", System.currentTimeMillis() - startTime, itemsProcessed);
    }

}
