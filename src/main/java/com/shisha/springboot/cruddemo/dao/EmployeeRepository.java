package com.shisha.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shisha.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
