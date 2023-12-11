package com.jamersc.springboot.employee.controller;

import com.jamersc.springboot.employee.entity.Employee;
import com.jamersc.springboot.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees") // base mapping for URL Request
public class EmployeeController {

    // add EmployeeService Class
    private EmployeeService employeeService;
    // Constructor Injection
    @Autowired // Autowired optional, use only if many constructor
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // add mapping for displaying list of employee
    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        List<Employee> theEmployees = employeeService.findAll();

        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    // add mapping to show employee create form
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create a new Employee Object
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    // add mapping for saving employee
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee
        // passing to employee service -> employee repository -> database / backend
        employeeService.save(theEmployee);

        // use redirect to prevent duplicate submission - redirect to request mapping employees/list
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

        // get the employee from the Employee Service Class
        Employee theEmployee = employeeService.findById(theId);

        // set the employee to populate the form
        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theId) {

        // delete selected employee
        employeeService.deleteById(theId);

        // use redirect to prevent duplicate submission - redirect to request mapping employees/list
        return "redirect:/employees/list";
    }

}
