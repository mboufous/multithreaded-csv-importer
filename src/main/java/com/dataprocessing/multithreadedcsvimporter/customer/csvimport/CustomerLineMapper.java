package com.dataprocessing.multithreadedcsvimporter.customer.csvimport;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import com.dataprocessing.multithreadedcsvimporter.customer.CustomerModel;
import com.dataprocessing.multithreadedcsvimporter.dataprocessing.mapper.LineMapper;
import com.dataprocessing.multithreadedcsvimporter.dataprocessing.mapper.LineMappingException;

@Component
public class CustomerLineMapper implements LineMapper<CustomerModel> {
    @Override
    public CustomerModel mapLine(final CSVRecord record) throws LineMappingException {
        try{
            CustomerModel customerModel = new CustomerModel();
            customerModel.setEmail(record.get("email"));
            customerModel.setFirstName(record.get("first_name"));
            customerModel.setLastName(record.get("last_name"));
            customerModel.setCity(record.get("city"));
            customerModel.setCountry(record.get("country"));
            customerModel.setPhone(record.get("phone1"));
            return customerModel;
        }catch(RuntimeException e){
            throw new LineMappingException("Error mapping line", e);
        }

    }
}
