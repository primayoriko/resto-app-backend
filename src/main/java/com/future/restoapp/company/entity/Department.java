package com.future.restoapp.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.future.restoapp.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = Department.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseEntity {

	public static final String TABLE_NAME = "department";

	public static final String COLUMN_DEPT_NO = "deptno";
	public static final String COLUMN_DEPT_NAME = "dname";
	public static final String COLUMN_LOC = "loc";

	@Column(name = Department.COLUMN_DEPT_NO)
	private Integer deptNo;

	@Column(name = Department.COLUMN_DEPT_NAME)
	private String deptName;

	@Column	(name = Department.COLUMN_LOC)
	private String loc;
}
