package com.shisha.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shisha.springboot.cruddemo.entity.Employee;
import com.shisha.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    // inject employee DAO constructor injection
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findall() {
        return employeeService.findAll();
    }

    // add mapping for GET /employess/{employeeId}

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        // in case if they pass an id in JSON .... set id 0
        // this is to force a save of new item ... instead of update.

        theEmployee.setId(0);

        employeeService.save(theEmployee);

        return theEmployee;

    }
    
    // add mapping for PUT /employees - update existing employee
    
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        
        employeeService.save(theEmployee);
        
        return theEmployee;
    }
    
    // add mapping for DELETE /employees/{employeeId} - delete existing employee
   @DeleteMapping("/employees/{employeeId}")
   private Employee deleteEmployee(@PathVariable int employeeId) {
    // private String deleteEmployee(@PathVariable int employeeId) {
       
       Employee theEmployee = employeeService.findById(employeeId);
       
       if (theEmployee == null) {
           throw new RuntimeException("Employee Id not found - " +employeeId);
       } 
        employeeService.deleteById(employeeId);
    
       return theEmployee;    
       // return "Deleted employee id - " + employeeId; 
   }

}
