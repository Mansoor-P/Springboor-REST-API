package com.mansoor.springboot.rest.repository;

import com.mansoor.springboot.rest.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TestEntityManager testEntityManager;


    @BeforeEach
    void setUp() {
        Department department=Department.builder()
                .departmentName("Mechanical Engineering")
                .departmentAddress("HYDRABAD")
                .departmentCode("MEH-2024")
                .build();
        testEntityManager.persist(department);

    }
    @Test
    public void whenFindById_thenReturnDepartment(){
        Department department=departmentRepository.findById(1L).get();
        assertEquals(department.getDepartmentName(),"Mechanical Engineering");
    }

}