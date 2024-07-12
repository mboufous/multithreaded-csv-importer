package com.dataprocessing.multithreadedcsvimporter.customer.csvimport;

import org.springframework.stereotype.Component;
import com.dataprocessing.multithreadedcsvimporter.customer.CustomerModel;
import com.dataprocessing.multithreadedcsvimporter.dataprocessing.importer.DefaultCSVImporter;

@Component
public class CustomerCSVImporter extends DefaultCSVImporter<CustomerModel> {

    public CustomerCSVImporter(final CustomerItemProcessor itemProcessor, final CustomerLineMapper lineMapper) {
        super(itemProcessor, lineMapper);
    }
}
