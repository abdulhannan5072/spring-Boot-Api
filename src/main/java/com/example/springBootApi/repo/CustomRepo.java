package com.example.springBootApi.repo;

import com.example.springBootApi.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomRepo extends MongoRepository<Employee, String> {

    // custom queries
    public Employee findByName(String name);

}
