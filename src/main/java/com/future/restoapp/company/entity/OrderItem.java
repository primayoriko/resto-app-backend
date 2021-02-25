package com.future.restoapp.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.demo.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import com.future.restoapp.company.entity.*;

@Table(name = com.future.restoapp.company.entity.OrderItem.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseEntity {
    public static final String TABLE_NAME = "order_item";

    public static final String COLUMN_ID_MENU = "id_menu";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_ID_RESERVATION = "id_reservation";

    @Column(name = OrderItem.COLUMN_ID_MENU)
    private Integer id_menu;

    @Column(name = OrderItem.COLUMN_QUANTITY)
    private Integer quantity;

    @Column(name = OrderItem.COLUMN_ID_RESERVATION)
    private Integer id_reservation;

}
