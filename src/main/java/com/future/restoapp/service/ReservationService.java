package com.future.restoapp.service;

import com.future.restoapp.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface ReservationService {

    Reservation create(@NotNull Reservation reservation) throws Exception;

    Reservation findById(long id) throws Exception;

    Page<Reservation> findByQuery(
            Long userId,
            Long boardId,
            Boolean isAccepted,
            LocalDateTime lowerStartTime,
            LocalDateTime upperStartTime,
            LocalDateTime lowerEndTime,
            LocalDateTime upperEndTime,
            Pageable pageable
    ) throws Exception;

    void deleteById(long id) throws Exception;

    Reservation update(@NotNull Reservation reservation) throws Exception;

}
