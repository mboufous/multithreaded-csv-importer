package com.dataprocessing.multithreadedcsvimporter.dataprocessing.importer;

import java.io.File;

public interface Importer {
    void startImport(File file);
}
