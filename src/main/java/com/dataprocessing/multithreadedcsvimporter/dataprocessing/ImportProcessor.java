package com.dataprocessing.multithreadedcsvimporter.dataprocessing;

import org.apache.commons.csv.CSVRecord;

public interface ImportProcessor {
    void process(final CSVRecord record);
}
