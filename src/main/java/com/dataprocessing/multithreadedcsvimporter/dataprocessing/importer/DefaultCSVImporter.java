package com.dataprocessing.multithreadedcsvimporter.dataprocessing.importer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import com.dataprocessing.multithreadedcsvimporter.dataprocessing.mapper.LineMapper;

@Slf4j
@RequiredArgsConstructor
public class DefaultCSVImporter<T> implements CSVImporter {

    @Value("${app.csv.import.batch-size}")
    private static final int DEFAULT_BATCH_SIZE = 1000;
    @Value("${app.csv.import.continue-on-error}")
    private boolean continueOnError = false;

    protected final ItemProcessor<T> itemProcessor;
    protected final LineMapper<T> lineMapper;

    private CSVFormat csvFormat = CSVFormat.DEFAULT;

    @Override
    public void startImport(final File file) {
        int itemsProcessed = 0;
        log.info("Import started, file: {}, size: {} MB", file.getName(), file.length() / 1024);
        long startTime = System.currentTimeMillis();

        try (Reader reader = Files.newBufferedReader(file.toPath());
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {
            for (CSVRecord record : csvParser) {
                try {
                    T item = lineMapper.mapLine(record);
                    itemProcessor.process(item);
                    itemsProcessed++;
                    if (itemsProcessed % DEFAULT_BATCH_SIZE == 0) {
                        log.info("{} items processed", itemsProcessed);
                    }
                } catch (Exception e) {
                    if (!continueOnError) {
                        log.error("Stopping import due to error.");
                        break;
                    }
                    log.error("Error processing a record at line {}", record.getRecordNumber(), e);
                }

            }
        } catch (IOException e) {
            log.error("Error importing CSV file: {}", file.getName(), e);
        }

        log.info("Import finished in {} ms ({} items processed)", System.currentTimeMillis() - startTime, itemsProcessed);
    }

    @Override
    public void setCsvFormat(CSVFormat csvFormat) {
        this.csvFormat = csvFormat;
    }
}
