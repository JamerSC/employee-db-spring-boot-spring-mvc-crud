package com.jamersc.springboot.employee.service;

import com.jamersc.springboot.employee.dao.EmployeeRepository;
import com.jamersc.springboot.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    // Constructor Injection
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        // return employeeRepository.findAll();

        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int theId) {
        // Note: Java 8 - code pattern
        // A container object which may or may not contain a non-null value.
        // Optional Approach
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee tempEmployee = null;

        if (result.isPresent()) {
            tempEmployee = result.get();
        }
        else {
            throw new RuntimeException("Did not find employee id -" + theId);
        }
        return tempEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
