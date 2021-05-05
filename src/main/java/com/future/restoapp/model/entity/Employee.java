package com.future.restoapp.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = Employee.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity {

	public static final String TABLE_NAME = "employee";

	public static final String COLUMN_EMP_NO = "empno";
	public static final String COLUMN_EMP_NAME = "ename";
	public static final String COLUMN_JOB = "job";
	public static final String COLUMN_MGR = "mgr";
	public static final String COLUMN_HIRE_DATE = "hiredate";
	public static final String COLUMN_SAL = "sal";
	public static final String COLUMN_COMM = "comm";
	public static final String COLUMN_DEPT_NO = "deptno";

	@Column(name = Employee.COLUMN_EMP_NO)
	private Integer empNo;

	@Column(name = Employee.COLUMN_EMP_NAME)
	private String empName;

	@Column(name = Employee.COLUMN_JOB)
	private String job;

	@Column(name = Employee.COLUMN_MGR)
	private Integer mgr;

	@Column(name = Employee.COLUMN_HIRE_DATE)
	private Date hireDate;

	@Column(name = Employee.COLUMN_SAL)
	private Double sal;

	@Column(name = Employee.COLUMN_COMM)
	private Double comm;

	@Column(name = Employee.COLUMN_DEPT_NO)
	private Integer deptNo;
}
