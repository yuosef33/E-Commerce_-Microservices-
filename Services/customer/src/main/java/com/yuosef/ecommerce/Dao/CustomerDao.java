package com.yuosef.ecommerce.Dao;

import com.yuosef.ecommerce.Models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends MongoRepository<Customer,String> {
}
