package com.dataprocessing.multithreadedcsvimporter.customer.repository;

import org.springframework.data.repository.CrudRepository;
import com.dataprocessing.multithreadedcsvimporter.customer.CustomerModel;

public interface CustomerRepository extends CrudRepository<CustomerModel, Long>{
}
