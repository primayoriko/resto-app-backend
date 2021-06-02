package com.future.restoapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.Board;
import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.OrderItem;
import com.future.restoapp.model.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateRequest implements Serializable {

    @NotNull(message = "boardId must be specified")
    @Positive(message = "boardId value must be positive")
    private Long boardId;

    @NotNull(message = "startTime must be specified")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @NotNull(message = "endTime must be specified")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    private Collection<OrderItemCreateRequest> orders = new HashSet<>();

    public Reservation toReservation(){
        return Optional.of(this).map(dto -> {
            Reservation reservation = new Reservation();
            Board board = new Board();

            BeanUtils.copyProperties(dto, reservation, "orders", "boardId");
            board.setId(boardId);
            reservation.setBoard(board);
            reservation.setOrders(
                    orders.stream()
                            .map(el -> {
                                Menu menu = new Menu();
                                OrderItem order = OrderItem.builder()
                                        .quantity(el.getQuantity())
                                        .menu(menu)
                                        .build();
                                menu.setId(el.getMenuId());
                                return order;
                            }).collect(Collectors.toSet())
            );

            return reservation;
        }).orElse(null);
    }

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

}
