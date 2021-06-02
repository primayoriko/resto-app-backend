package com.future.restoapp.service;

import com.future.restoapp.model.entity.Reservation;
import com.future.restoapp.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ReservationService {

    Reservation create(Reservation reservation, User client) throws Exception;

    Reservation findById(Long id) throws Exception;

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

}
