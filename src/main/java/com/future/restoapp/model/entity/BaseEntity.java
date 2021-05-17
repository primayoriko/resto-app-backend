package com.future.restoapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@Data
@Builder(builderMethodName = "superBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

  public static final String COLUMN_ID = "id";
  public static final String COLUMN_CREATED_DATE = "created_date";
  public static final String COLUMN_UPDATED_DATE = "updated_date";
  //  public static final String COLUMN_VERSION = "version";

  @Id
  @Column(name = BaseEntity.COLUMN_ID)
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @org.springframework.data.annotation.Id
  private String id;

//  @Version
//  @Column(name = BaseEntity.COLUMN_VERSION)
//  @Builder.Default
//  private Long version = 0L;

  @CreatedDate
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name = BaseEntity.COLUMN_CREATED_DATE, nullable = false, updatable = false)
  private Date createdDate = new Date();

  @LastModifiedDate
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name = BaseEntity.COLUMN_UPDATED_DATE, nullable = false)
  private Date updatedDate = new Date();

}
