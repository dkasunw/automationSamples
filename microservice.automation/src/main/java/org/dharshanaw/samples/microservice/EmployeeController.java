package org.dharshanaw.samples.microservice;

import java.net.URI;

import org.dharshanaw.samples.microservice.dao.EmployeeDAO;
import org.dharshanaw.samples.microservice.model.Employee;
import org.dharshanaw.samples.microservice.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController
{
    @Autowired
    private EmployeeDAO employeeDao;

    @GetMapping(path="/", produces = "application/json")
    public ResponseEntity<Employees> getEmployees()
    {
        return new ResponseEntity<Employees>(employeeDao.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping(path= "/adduser/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee)
    {
        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);

        employeeDao.addEmployee(employee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}