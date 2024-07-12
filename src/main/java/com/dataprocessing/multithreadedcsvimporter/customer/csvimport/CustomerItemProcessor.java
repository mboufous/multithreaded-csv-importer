package com.dataprocessing.multithreadedcsvimporter.customer.csvimport;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import com.dataprocessing.multithreadedcsvimporter.customer.CustomerModel;
import com.dataprocessing.multithreadedcsvimporter.customer.repository.CustomerRepository;
import com.dataprocessing.multithreadedcsvimporter.dataprocessing.importer.ItemProcessor;

@Component
@RequiredArgsConstructor
public class CustomerItemProcessor implements ItemProcessor<CustomerModel> {

    private final CustomerRepository customerRepository;

    @Override
    public void process(final CustomerModel customer) {
        customerRepository.save(customer);
    }
}
