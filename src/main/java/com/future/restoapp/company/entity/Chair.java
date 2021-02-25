package com.future.restoapp.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.future.restoapp.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import com.future.restoapp.company.entity.*;

@Table(name = com.future.restoapp.company.entity.Chair.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chair extends BaseEntity {
    public static final String TABLE_NAME = "chairs";

    public static final String COLUMN_X = "x";
    public static final String COLUMN_Y = "y";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TABLE_ID = "table_id";

    @Column(name = Chair.COLUMN_X)
    private Integer x;

    @Column(name = Chair.COLUMN_Y)
    private Integer y;

    @Column(name = Chair.COLUMN_ID)
    private Integer id;

    @Column(name = Chair.COLUMN_TABLE_ID)
    private Integer tableID;
}
