package com.dataprocessing.multithreadedcsvimporter.dataprocessing.mapper;

import org.apache.commons.csv.CSVRecord;

public interface LineMapper<T> {
    T mapLine(CSVRecord record) throws LineMappingException;
}
