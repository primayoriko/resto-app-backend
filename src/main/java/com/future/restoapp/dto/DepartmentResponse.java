package com.future.restoapp.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.future.restoapp.company.entity.Department;
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
public class DepartmentResponse implements Serializable {

	private Integer deptNo;
	private String deptName;
	private String loc;
	
	private String id;
	private boolean markForDelete = false;
	private Long version = 0L;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String storeId;
}
