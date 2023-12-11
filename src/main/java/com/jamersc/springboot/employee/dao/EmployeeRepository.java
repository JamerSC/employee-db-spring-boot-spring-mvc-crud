package com.jamersc.springboot.employee.dao;

import com.jamersc.springboot.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /*
        Spring DATA JPA
        No need for implementation of class
        JpaRepository<Entity type, Primary key>
     */

    // custom query
    // add a method to sort by last name
    public List<Employee> findAllByOrderByLastNameAsc();

}
