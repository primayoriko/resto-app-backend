package com.future.restoapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    public static final String COLUMN_IMAGE_FILENAME = "image_filename";

    @Column(name = Menu.COLUMN_NAME, unique = true, nullable = false)
    private String name;

    @Column(name = Menu.COLUMN_CATEGORY, nullable = false)
    private String category;

    @Column(name = Menu.COLUMN_PRICE, nullable = false)
    private Float price;

    @Column(name = Menu.COLUMN_STOCK, nullable = false)
    private Integer stock;

    @Column(name = Menu.COLUMN_DESCRIPTION)
    private String description;

    @Column(name = Menu.COLUMN_IMAGE_FILENAME)
    private String imageFilename;

//    @OneToMany(mappedBy = TABLE_NAME)
//    private Collection<OrderItem> orders = new HashSet<>();

}
