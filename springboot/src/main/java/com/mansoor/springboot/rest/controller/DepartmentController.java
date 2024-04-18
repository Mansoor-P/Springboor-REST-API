package com.mansoor.springboot.rest.controller;

import com.mansoor.springboot.rest.entity.Department;
import com.mansoor.springboot.rest.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    private final Logger LOGGER =LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/departments")
    public Department saveDepartment(@Valid  @RequestBody Department department) {
        LOGGER.info("The Logger is Called from Inside saveDepartment of DepartmentController");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartmentList() {
        return departmentService.fetchDepartmentList();
    }

    @GetMapping("/departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) {
        return departmentService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return "Department Deleted Successfully !!";
    }

    @PutMapping("/department/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId,
                                      @RequestBody Department department) {

        return departmentService.updateDepartment(departmentId,department);
    }
    @GetMapping("/deparments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name")  String departmentName){
        return departmentService.fetchDepartmentByName(departmentName);
    }


}
