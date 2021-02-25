package com.demo.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.company.entity.Department;

public interface DepartementRepository extends JpaRepository<Department, String> {

	Department findFirstByDeptNoAndMarkForDeleteFalse(Integer deptNo);

}
