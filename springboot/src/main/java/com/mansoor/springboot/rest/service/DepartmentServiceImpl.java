package com.mansoor.springboot.rest.service;

import com.mansoor.springboot.rest.entity.Department;
import com.mansoor.springboot.rest.error.DepartmentNotFoundException;
import com.mansoor.springboot.rest.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!department.isPresent()){
            throw  new DepartmentNotFoundException("Department Not Available");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Optional<Department> optionalDepDB = departmentRepository.findById(departmentId);
        if (optionalDepDB.isPresent()) {
            Department depDB = optionalDepDB.get();

            // Update attributes if not null and not empty
            if (Objects.nonNull(department.getDepartmentName()) && !department.getDepartmentName().isEmpty()) {
                depDB.setDepartmentName(department.getDepartmentName());
            }
            if (Objects.nonNull(department.getDepartmentAddress()) && !department.getDepartmentAddress().isEmpty()) {
                depDB.setDepartmentAddress(department.getDepartmentAddress());
            }
            if (Objects.nonNull(department.getDepartmentCode()) && !department.getDepartmentCode().isEmpty()) {
                depDB.setDepartmentCode(department.getDepartmentCode());
            }

            // Save the updated department
            return departmentRepository.save(depDB);
        } else {
            return null; // or throw an exception
        }
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}
