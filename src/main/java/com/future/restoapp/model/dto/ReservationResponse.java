package com.future.restoapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.Menu;
import com.future.restoapp.model.entity.Menu.MenuCategory;
import com.future.restoapp.model.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse implements Serializable {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Boolean isAccepted;

    private Float totalPrice = 0F;

    private Collection<OrderItemResponse> orders = new HashSet<>();

    public static ReservationResponse build(Reservation r){
        return ReservationResponse
                .builder()
                .id(r.getId())
                .startTime(r.getStartTime())
                .endTime(r.getEndTime())
                .isAccepted(r.getIsAccepted())
                .totalPrice(r.getTotalPrice())
                .orders(
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
                                        .imageFilename(menu.getImageFilename())
                                        .quantity(el.getQuantity())
                                        .build();
                            }).collect(Collectors.toSet())
                ).build();
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

        private String imageFilename;

        private Integer quantity;

    }

}
