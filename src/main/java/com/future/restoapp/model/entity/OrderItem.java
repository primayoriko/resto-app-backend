package com.future.restoapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = OrderItem.TABLE_NAME)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem implements Serializable {
    public static final String TABLE_NAME = "order_items";
    public static final String COLUMN_QUANTITY = "quantity";

    public static final String FIELD_RESERVATION_ID = "reservationId";
    public static final String FIELD_MENU_ID = "menuId";

    @EmbeddedId
    OrderItemKey id;

    @ManyToOne
    @MapsId(FIELD_RESERVATION_ID)
    @JoinColumn(name = OrderItemKey.COLUMN_RESERVATION_ID)
    Reservation reservation;

    @ManyToOne
    @MapsId(FIELD_MENU_ID)
    @JoinColumn(name = OrderItemKey.COLUMN_MENU_ID)
    Menu menu;

    @Column(name = OrderItem.COLUMN_QUANTITY)
    Integer quantity;

}
