package com.future.restoapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class OrderItemKey implements Serializable {
    public static final String COLUMN_RESERVATION_ID = "reservation_id";
    public static final String COLUMN_MENU_ID = "menu_id";

    @Column(name = COLUMN_RESERVATION_ID)
    String reservationId;

    @Column(name = COLUMN_MENU_ID)
    String menuId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemKey)) return false;

        OrderItemKey orderItemKey = (OrderItemKey) o;
        return Objects.equals(reservationId, orderItemKey.getReservationId()) &&
                Objects.equals(menuId, orderItemKey.getMenuId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, menuId);
    }

}

@Entity
@Table(name = OrderItem.TABLE_NAME)
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
