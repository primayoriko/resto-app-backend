package com.future.restoapp.company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.future.restoapp.company.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

//	Employee findFirstByEmpNoAndMarkForDeleteFalse(Integer empNo);
//
//	Page<Employee> findByMarkForDeleteFalse(Pageable pageable);
//
//	void deleteByEmpNo(Integer empNo);
}
