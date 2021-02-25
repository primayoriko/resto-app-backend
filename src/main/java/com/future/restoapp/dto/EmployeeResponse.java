package com.demo.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse implements Serializable {

	private Integer empNo;
	private String empName;
	private String job;
	private Integer mgr;
	private Double sal;
	private Double comm;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
	
	private DepartmentResponse department;

	private String id;
	private boolean markForDelete = false;
	private Long version = 0L;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String storeId;
}
