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

@Table(name = Board.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity{
    public static final String TABLE_NAME = "boards";

    public static final String COLUMN_X = "x";
    public static final String COLUMN_Y = "y";
    public static final String COLUMN_WIDTH = "width";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_ID = "id";

    @Column(name = Board.COLUMN_X)
    private Integer x;

    @Column(name = Board.COLUMN_Y)
    private Integer y;

    @Column(name = Board.COLUMN_WIDTH)
    private Integer width;

    @Column(name = Board.COLUMN_HEIGHT)
    private Integer height;

    @Column(name = Board.COLUMN_ID)
    private Integer id;

}
