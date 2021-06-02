package com.future.restoapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemKey implements Serializable {
    public static final String COLUMN_RESERVATION_ID = "reservation_id";
    public static final String COLUMN_MENU_ID = "menu_id";

    @Column(name = COLUMN_RESERVATION_ID)
    private Long reservationId;

    @Column(name = COLUMN_MENU_ID)
    private Long menuId;

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
