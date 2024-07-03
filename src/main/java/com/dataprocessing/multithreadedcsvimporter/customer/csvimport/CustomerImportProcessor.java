package com.dataprocessing.multithreadedcsvimporter.customer.csvimport;

import lombok.RequiredArgsConstructor;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import com.dataprocessing.multithreadedcsvimporter.customer.CustomerModel;
import com.dataprocessing.multithreadedcsvimporter.customer.repository.CustomerRepository;
import com.dataprocessing.multithreadedcsvimporter.dataprocessing.ImportProcessor;

@Component
@RequiredArgsConstructor
public class CustomerImportProcessor implements ImportProcessor {

    private final CustomerRepository customerRepository;

    @Override
    public void process(final CSVRecord record) {
        CustomerModel customerModel = convertToCustomerModel(record);
        customerRepository.save(customerModel);
    }

    private CustomerModel convertToCustomerModel(final CSVRecord customerRecord) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setEmail(customerRecord.get("email"));
        customerModel.setFirstName(customerRecord.get("first_name"));
        customerModel.setLastName(customerRecord.get("last_name"));
        customerModel.setCity(customerRecord.get("city"));
        customerModel.setCountry(customerRecord.get("country"));
        customerModel.setPhone(customerRecord.get("phone1"));
        return customerModel;
    }
}
