package com.future.restoapp.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
class OrderItemKey implements Serializable {
    public static final String COLUMN_RESERVATION_ID = "reservation_id";
    public static final String COLUMN_MENU_ID = "menu_id";

    @Column(name = COLUMN_RESERVATION_ID)
    String reservationId;

    @Column(name = COLUMN_MENU_ID)
    String menuId;

//    public boolean equals(@NotNull OrderItemKey other){
//        return reservationId.equals(other.reservationId)
//                && menuId.equals(other.menuId);
//    }

}

@Entity
@Table(name = OrderItem.TABLE_NAME)
public class OrderItem implements Serializable {
    public static final String TABLE_NAME = "order_items";
    public static final String COLUMN_QUANTITY = "quantity";

    public static final String COLUMN_RESERVATION_ID = "reservationId";
    public static final String COLUMN_MENU_ID = "menuId";

    @EmbeddedId
    OrderItemKey id;

    @ManyToOne
    @MapsId(COLUMN_RESERVATION_ID)
    @JoinColumn(name = OrderItemKey.COLUMN_RESERVATION_ID)
    Reservation reservation;

    @ManyToOne
    @MapsId(COLUMN_MENU_ID)
    @JoinColumn(name = OrderItemKey.COLUMN_MENU_ID)
    Menu menu;

    @Column(name = OrderItem.COLUMN_QUANTITY)
    Integer quantity;

}
