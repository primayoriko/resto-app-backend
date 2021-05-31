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
import java.time.LocalDateTime;

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
//  @org.springframework.data.annotation.Id
//  @GeneratedValue(generator = "uuid")
//  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private Long id;

//  @Version
//  @Column(name = BaseEntity.COLUMN_VERSION)
//  @Builder.Default
//  private Long version = 0L;

  //  @Temporal(value = TemporalType.TIMESTAMP)
  @CreatedDate
  @Column(
          name = BaseEntity.COLUMN_CREATED_DATE,
          columnDefinition = "TIMESTAMP",
          nullable = false,
          updatable = false
  )
  private LocalDateTime createdDate = LocalDateTime.now();

  //  @Temporal(value = TemporalType.TIMESTAMP)
  @LastModifiedDate
  @Column(
          name = BaseEntity.COLUMN_UPDATED_DATE,
          columnDefinition = "TIMESTAMP",
          nullable = false
  )
  private LocalDateTime updatedDate = LocalDateTime.now();

}
