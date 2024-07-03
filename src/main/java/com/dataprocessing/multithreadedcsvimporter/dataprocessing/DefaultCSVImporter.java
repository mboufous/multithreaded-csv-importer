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
public class DefaultCSVImporter implements CSVImporter {

    private final ImportProcessor importProcessor;
    private int itemsProcessed = 0;
    private CSVFormat csvFormat = CSVFormat.DEFAULT;
    private static final int DEFAULT_BATCH_SIZE = 1000;

    @Override
    public void setCsvFormat(CSVFormat csvFormat) {
        this.csvFormat = csvFormat;
    }

    @Override
    public void startImport(final File file) {
        log.info("Import started, file: {}, size: {} MB", file.getName(), file.length() / 1024);
        long startTime = System.currentTimeMillis();

        try (Reader reader = Files.newBufferedReader(file.toPath());

             CSVParser csvParser = new CSVParser(reader, csvFormat)) {
            for (CSVRecord record : csvParser) {
                importProcessor.process(record);
                itemsProcessed++;
                if (itemsProcessed % DEFAULT_BATCH_SIZE == 0) {
                    log.info("{} items processed", itemsProcessed);
                }
            }
        } catch (IOException e) {
            log.error("Error importing CSV file", e);
        }

        log.info("Import finished in {} ms ({} items processed)", System.currentTimeMillis() - startTime, itemsProcessed);
    }

}
