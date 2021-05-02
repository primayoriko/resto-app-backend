package com.future.restoapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.future.restoapp.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import com.future.restoapp.model.entity.*;

@Table(name = com.future.restoapp.model.entity.Menu.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity {
    public static final String TABLE_NAME = "menus";

    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_STOCK = "stock";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DESCRIPTION = "description";

    @Column(name = Menu.COLUMN_CATEGORY)
    private String category;

    @Column(name = Menu.COLUMN_NAME)
    private String name;

    @Column(name = Menu.COLUMN_STOCK)
    private Integer stock;

    @Column(name = Menu.COLUMN_PRICE)
    private Float price;

    @Column(name = Menu.COLUMN_DESCRIPTION)
    private String description;
}
