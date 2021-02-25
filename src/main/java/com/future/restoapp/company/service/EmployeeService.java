package com.future.restoapp.company.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.future.restoapp.company.entity.Department;
import com.future.restoapp.company.entity.Employee;

public interface EmployeeService {

	void create(Employee employee) throws Exception;

	void update(Integer empNo, Employee employee) throws Exception;

	void updateName(Integer code, Employee employee) throws Exception;

	Page<Employee> find(Pageable pageable) throws Exception;

	Employee findByCode(Integer code) throws Exception;

	void deleteByEmpNo(Integer code) throws Exception;

	Department findDepartmentByDeptNo(Integer deptNo);

}
