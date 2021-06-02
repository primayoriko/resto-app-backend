package com.future.restoapp.model.dto.reservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.future.restoapp.model.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationAdminUpdateRequest implements Serializable {

    @NotNull(message = "id must be specified")
    @Positive(message = "id value must be positive")
    private Long id;

    @NotNull(message = "isAccepted must be specified")
    private Boolean isAccepted;

    public Reservation toReservation(){
        return Optional.of(this).map(dto -> {
            Reservation reservation = Reservation.builder().build();
            BeanUtils.copyProperties(dto, reservation);
            return reservation;
        }).orElse(null);
    }

}
