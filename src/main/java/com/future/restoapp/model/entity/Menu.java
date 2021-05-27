package com.future.restoapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Locale;

@Table(name = Menu.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity {
    public static final String TABLE_NAME = "menus";

    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IS_SOLD = "is_sold";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE_FILENAME = "image_filename";

    @Column(name = Menu.COLUMN_NAME, unique = true, nullable = false, updatable = false)
    private String name;

    @Column(name = Menu.COLUMN_CATEGORY, nullable = false, updatable = false)
    @Enumerated(EnumType.ORDINAL)
    private MenuCategory category;

    @Column(name = Menu.COLUMN_DESCRIPTION, updatable = false)
    private String description;

    @Column(name = Menu.COLUMN_IMAGE_FILENAME, updatable = false)
    private String imageFilename;

    @Column(name = Menu.COLUMN_PRICE, nullable = false)
    private Float price;

    @Column(name = COLUMN_IS_SOLD, nullable = false)
    private Boolean isSold = true;

//    @OneToMany(mappedBy = "menu")
//    private Collection<OrderItem> orders = new HashSet<>();

    public enum MenuCategory {
        FOOD, DRINK, OTHER;

        public static MenuCategory of(String value){
            if(value.toUpperCase(Locale.ROOT).equals("FOOD")) return MenuCategory.FOOD;
            else if(value.toUpperCase(Locale.ROOT).equals("DRINK")) return MenuCategory.DRINK;
            return MenuCategory.OTHER;
        }
    }

}
