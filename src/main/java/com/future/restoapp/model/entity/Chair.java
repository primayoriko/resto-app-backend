package com.future.restoapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import com.future.restoapp.model.entity.*;

@Table(name = com.future.restoapp.model.entity.Chair.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chair extends BaseEntity {
    public static final String TABLE_NAME = "chairs";

    public static final String COLUMN_X = "x";
    public static final String COLUMN_Y = "y";
    public static final String COLUMN_TABLE_ID = "table_id";

    @Column(name = Chair.COLUMN_X)
    private Integer x;

    @Column(name = Chair.COLUMN_Y)
    private Integer y;

    @Column(name = Chair.COLUMN_TABLE_ID)
    private Integer tableID;
}
