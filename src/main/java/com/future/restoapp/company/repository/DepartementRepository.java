package com.future.restoapp.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.future.restoapp.company.entity.Department;

public interface DepartementRepository extends JpaRepository<Department, String> {

	Department findFirstByDeptNoAndMarkForDeleteFalse(Integer deptNo);

}
