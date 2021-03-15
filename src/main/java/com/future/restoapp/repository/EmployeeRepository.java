package com.future.restoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.future.restoapp.model.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

//	Employee findFirstByEmpNoAndMarkForDeleteFalse(Integer empNo);
//
//	Page<Employee> findByMarkForDeleteFalse(Pageable pageable);
//
//	void deleteByEmpNo(Integer empNo);
}
