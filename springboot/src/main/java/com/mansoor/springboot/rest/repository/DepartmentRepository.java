package com.mansoor.springboot.rest.repository;

import com.mansoor.springboot.rest.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department findByDepartmentName(String departmentName);

    Department findByDepartmentNameIgnoreCase(String departmentName);
}
