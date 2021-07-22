package com.future.restoapp.dto.reservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.dto.user.UserResponse;
import com.future.restoapp.dto.board.BoardResponse;
import com.future.restoapp.domain.Menu;
import com.future.restoapp.domain.Menu.MenuCategory;
import com.future.restoapp.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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
public class ReservationResponse implements Serializable {

    private Long id;

    private UserResponse user;

    private BoardResponse board;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Boolean isAccepted;

    private Float totalPrice = 0F;

    private Collection<OrderItemResponse> orders = new HashSet<>();

    public static ReservationResponse build(Reservation r){
        return Optional.of(r).map(entity -> {
            ReservationResponse response = new ReservationResponse();

            BeanUtils.copyProperties(entity, response, "orders", "board", "user");
            response.setUser(UserResponse.build(r.getUser()));
            response.setBoard(BoardResponse.build(r.getBoard()));
            response.setOrders(
                    r.getOrders()
                            .stream()
                            .map(el -> {
                                Menu menu = el.getMenu();
                                return OrderItemResponse
                                        .builder()
                                        .id(menu.getId())
                                        .name(menu.getName())
                                        .category(menu.getCategory())
                                        .description(menu.getDescription())
                                        .imageUrl(menu.getImageUrl())
                                        .quantity(el.getQuantity())
                                        .build();
                            }).collect(Collectors.toSet())
            );

            return response;
        }).orElse(null);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemResponse implements Serializable {

        private Long id;

        private String name;

        private MenuCategory category;

        private String description;

        private String imageUrl;

        private Integer quantity;

    }

}
