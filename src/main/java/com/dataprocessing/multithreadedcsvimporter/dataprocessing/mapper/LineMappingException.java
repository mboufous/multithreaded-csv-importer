package com.dataprocessing.multithreadedcsvimporter.dataprocessing.mapper;

public class LineMappingException extends Exception {
    public LineMappingException(final String message, final RuntimeException e) {
        super(message, e);
    }
}
