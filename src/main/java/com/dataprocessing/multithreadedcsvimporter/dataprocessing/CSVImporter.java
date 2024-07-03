package com.dataprocessing.multithreadedcsvimporter.dataprocessing;

import org.apache.commons.csv.CSVFormat;

public interface CSVImporter extends Importer {
    void setCsvFormat(CSVFormat csvFormat);
}
