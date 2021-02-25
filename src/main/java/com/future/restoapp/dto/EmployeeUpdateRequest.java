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
public class EmployeeUpdateRequest implements Serializable {

	private String empName;
	private String job;
	private Integer mgr;
	private Double sal;
	private Double comm;
	private Integer deptNo;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
}
