package com.dataprocessing.multithreadedcsvimporter.dataprocessing;

import java.io.File;

public interface Importer {
    void startImport(File file);
}
