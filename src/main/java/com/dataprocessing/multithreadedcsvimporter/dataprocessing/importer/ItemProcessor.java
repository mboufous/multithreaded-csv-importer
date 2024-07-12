package com.dataprocessing.multithreadedcsvimporter.dataprocessing.importer;

public interface ItemProcessor<T> {
    void process(final T item);
}
