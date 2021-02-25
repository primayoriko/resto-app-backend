package com.future.restoapp.company.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
public class OrderItem {
    public static final String TABLE_NAME = "order_items";

    public static final String COLUMN_MENU_ID = "menu_id";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_RESERVATION_ID = "reservation_id";

    @Id
    @Column(name = OrderItem.COLUMN_MENU_ID)
    private Integer menuID;

    @Id
    @Column(name = OrderItem.COLUMN_RESERVATION_ID)
    private Integer reservationID;

    @Column(name = OrderItem.COLUMN_QUANTITY)
    private Integer quantity;

}
