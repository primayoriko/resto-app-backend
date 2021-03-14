package com.future.restoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.future.restoapp.entity.Department;

public interface DepartementRepository extends JpaRepository<Department, String> {

//	Department findFirstByDeptNoAndMarkForDeleteFalse(Integer deptNo);

}
