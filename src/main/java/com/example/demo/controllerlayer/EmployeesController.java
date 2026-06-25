 package com.example.demo.controllerlayer;

import com.example.demo.classlayer.Employees;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class EmployeesController
{
    HashMap<Integer, Employees> employees = new HashMap<>();

    @PostMapping("/employees")
    public Employees addEmployee(@RequestBody Employees employee)
    {
        employees.put(employee.getId(), employee);
        return employee;
    }

    @GetMapping("/employees")
    public Collection<Employees> getEmployees()
    {
        return employees.values();
    }

    @GetMapping("/employees/{id}")
    public Employees getEmployeeById(@PathVariable Integer id)
    {
        return employees.get(id);
    }

    @PutMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Integer id, @RequestBody Employees updatedEmployee) {

        Employees existingEmployee = employees.get(id);

        if(existingEmployee == null)
        {
            return "Employee Not Found";
        }

        Optional.ofNullable(updatedEmployee.getName())
                .ifPresent(existingEmployee::setName);

        Optional.ofNullable(updatedEmployee.getDepartment())
                .ifPresent(existingEmployee::setDepartment);
        
        return "Employee Updated Successfully";

    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Integer id)
    {
        if(employees.containsKey(id))
        {
            employees.remove(id);
            return "Employee Deleted Successfully";
        }
        return "Employee Not Found";
    }
}

