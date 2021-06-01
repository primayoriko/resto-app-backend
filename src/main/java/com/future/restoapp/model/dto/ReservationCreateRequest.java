package com.future.restoapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.OrderItem;
import com.future.restoapp.model.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateRequest implements Serializable {

    @NotNull(message = "startTime must be specified")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date startTime;

    @NotNull(message = "endTime must be specified")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date endTime;

    private Collection<OrderItemCreateRequest> orders = new HashSet<>();

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemCreateRequest implements Serializable {

        @NotNull(message = "menuId must be specified")
        @Positive(message = "menuId value must be positive")
        private Long menuId;

        @NotNull(message = "quantity must be specified")
        @Positive(message = "quantity value must be positive")
        private Integer quantity;

    }

    public Reservation toReservation(){
        Reservation reservation = Reservation.builder()
                .startTime(startTime)
                .endTime(endTime)
                .isAccepted(false)
                .totalPrice(0F)
                .orders(
                        orders.stream()
                            .map(el -> {
                                Menu menu = Menu.builder().build();
                                OrderItem order = OrderItem.builder()
                                        .quantity(el.getQuantity())
                                        .menu(menu)
                                        .build();

                                menu.setId(el.getMenuId());
                                System.out.println(order);

                                return order;
                            }).collect(Collectors.toSet())
                )
                .build();

        System.out.println(reservation);
        return reservation;
    }

}
