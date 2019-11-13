package org.dharshanaw.samples.microservice.test;

import javafx.application.Application;
import org.dharshanaw.samples.microservice.EmployeeApplication;
import org.dharshanaw.samples.microservice.EmployeeController;
import org.dharshanaw.samples.microservice.model.Employee;
import org.dharshanaw.samples.microservice.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = EmployeeApplication.class)
public class EmployeeServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterTest
    public void resetDb() {
        //repository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateEmployee() throws IOException, Exception {
       // Employee bob = new Employee("bob");
      //  mvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bob)));

       // List<Employee> found = repository.findAll();
      //  assertThat(found).extracting(Employee::getName).containsOnly("bob");
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
       // createTestEmployee("bob");
      //  createTestEmployee("alex");
        mockMvc.perform(get("/employees/").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is("bob")))
                .andExpect(jsonPath("$[1].name", is("alex")));
    }

    /*  */

    private void createTestEmployee(String name) {
       // Employee emp = new Employee(name);
       // repository.saveAndFlush(emp);
    }
}
