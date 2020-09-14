package com.example.springBootApi.controller;

import com.example.springBootApi.SpringBootApiApplication;
import com.example.springBootApi.model.Employee;
import com.example.springBootApi.repo.CustomRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    private final CustomRepo repository;
    private static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeController(CustomRepo repository) {
        this.repository = repository;
    }

//		LOGGER.debug("Debug level log message");
//		LOGGER.error("Error level log message");

    // create
    @PostMapping("/addEmployee")
    public String saveEmployee (@RequestBody Employee employee){
        long start = System.nanoTime();
        repository.save(employee);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        long milliseconds = timeElapsed / 1000000;
        LOGGER.info("Time of add : " + milliseconds);

        return "Added Employee";
    }

    // get all data
    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees (){
        long start = System.nanoTime();
        List<Employee> data = repository.findAll();
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        long milliseconds = timeElapsed / 1000000;

        LOGGER.info("Time of get all data : " + milliseconds);

        return data;
    }

    // get data by name
    @GetMapping("/getByName")
    public Employee getByName (@RequestParam String name){
        long start = System.nanoTime();
        Employee data = repository.findByName(name);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        long milliseconds = timeElapsed / 1000000;

        LOGGER.info("Time of get by name : " + milliseconds);
        return data;
    }

    // update
    @PostMapping("/updateEmployee/{id}")
    public Employee updateEmployee (@PathVariable("id") String id, @RequestBody Employee emp){
        long start = System.nanoTime();
        Optional<Employee> e = repository.findById(id);

        if(e.isPresent()){
            Employee data = e.get();
            data.setName(emp.getName());
            data.setPhoneNo(emp.getPhoneNo());
            Employee temp = repository.save(data);
            long finish = System.nanoTime();
            long timeElapsed = finish - start;
            long milliseconds = timeElapsed / 1000000;

            LOGGER.info("Time of update : " + milliseconds);
            return temp;
        } else {
            long finish = System.nanoTime();
            long timeElapsed = finish - start;
            long milliseconds = timeElapsed / 1000000;
            LOGGER.info("Time of update error : " + milliseconds);
            return null;
        }
    }

    // delete
    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee (@PathVariable("id") String id){

        long start = System.nanoTime();
        repository.deleteById(id);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        long milliseconds = timeElapsed / 1000000;

        LOGGER.info("Time of delete : " + milliseconds);
        return "Employee deleted";

    }



}
